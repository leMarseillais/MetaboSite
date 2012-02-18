package org.metabosite.module.controllers.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

import src.EJBKException;
import src.entities.Siteuser;
import src.services.SiteUserFacadeLocal;
import org.metabosite.module.controllers.user.security.Access;
import org.metabosite.module.controllers.user.security.Page;
import org.metabosite.module.controllers.user.security.RightID;
import org.metabosite.module.controllers.user.security.RightsManager;
import org.metabosite.module.utils.Global;
import org.metabosite.module.utils.JsfUtil;
import org.metabosite.module.utils.Nav;
import org.metabosite.module.utils.Reinitialize;
import org.metabosite.module.utils.Session;
import org.metabosite.module.utils.Bundle;

@ManagedBean(name = "utilisateurController")
@SessionScoped
public class UserController implements Serializable, Reinitialize {

	private static final long serialVersionUID = 1L;
	
	private Siteuser user = new Siteuser("ghf", true, "vgc su", RightsManager
			.getInstance().toLong(Access.ADMIN), "gsc uy@biscb.ci", "kbci");
	private DataModel<Siteuser> items = null;
	private DataModel<Siteuser> userItems = null;

	@EJB
	private SiteUserFacadeLocal ejbFacadeLocal;

	private Access access;
	private List<Access> accesses = new ArrayList<Access>();

	private Siteuser userCo;
	private String login;
	private String password;

	public List<Access> getAccessArray() {
		accesses.clear();

		Siteuser user = getUserCo();
		RightsManager rm = RightsManager.getInstance();
		for (Access a : Access.values()) {
			if (!user.has(rm.toLong(a), Siteuser.INF) && !user.is(rm.toLong(a))
					|| user.is(rm.toLong(Access.ROOT))) {

				if (!a.equals(Access.MIN) && !a.equals(Access.GUEST)) {
					accesses.add(a);
				}
			}
		}
		return accesses;
	}

	public Siteuser getUserCo() {
		userCo = (Siteuser) Global.getSession(Session.UserCo);
		return userCo;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public Siteuser getUser() {
		return user;
	}

	public void setUser(Siteuser user) {
		this.user = user;
	}

	public void setItems(DataModel<Siteuser> items) {
		this.items = items;
	}

	public void setUserItems(DataModel<Siteuser> userItems) {
		this.userItems = userItems;
	}

	public SiteUserFacadeLocal getEjbFacadeLocal() {
		return ejbFacadeLocal;
	}

	public void setEjbFacadeLocal(SiteUserFacadeLocal ejbFacadeLocal) {
		this.ejbFacadeLocal = ejbFacadeLocal;
	}

	public List<Access> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<Access> accesses) {
		this.accesses = accesses;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserCo(Siteuser userCo) {
		this.userCo = userCo;
	}

	public UserController() {
		Global.addUserSession(this);
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		Global.removeUserSession(this);
	}

	public Siteuser getSelected() {
		if (paramExists()) {
			DataModel<Siteuser> dmu = getItems();
			if (dmu.isRowAvailable()) {
				this.user = dmu.getRowData();
			} else {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpServletRequestWrapper req = (HttpServletRequestWrapper) fc
						.getExternalContext().getRequest();
				HttpServletResponseWrapper resp = (HttpServletResponseWrapper) fc
						.getExternalContext().getResponse();
				try {
					resp.sendRedirect(req.getContextPath()
							+ req.getServletPath() + Page.Index.getPath());
				} catch (IOException ex) {
					Logger.getLogger(UserController.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IllegalStateException ex) {
				}
			}
		}
		if (this.user == null) {
			this.user = new Siteuser();
		}
		return this.user;
	}

	private boolean paramExists() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequestWrapper req = (HttpServletRequestWrapper) fc
				.getExternalContext().getRequest();
		String pseudoParam = req.getParameter("pseudo");
		if (pseudoParam == null) {
			return false;
		}
		return true;
	}

	private void recreateModel() {
		items = null;
	}

	public String prepareList() {
		recreateModel();
		return Nav.UList.getDestination();
	}

	public String prepareView() {
		this.user = getItems().getRowData();
		return "View";
	}

	public String prepareCreate() {
		this.user = new Siteuser();
		return "Create";
	}

	public String create() {
		// Création
		ResourceBundle mbfe_rb = Bundle.MBFE.getBundle();
		try {
			this.user.setDroit(Global.getRightsManager().toLong(Access.ADMIN));

			Boolean work = getEjbFacadeLocal().create(this.user);
			if (work) {
				JsfUtil.addSuccessMessage(mbfe_rb
						.getString("UtilisateurCreated"));
			} else {
				JsfUtil.addErrorMessage(mbfe_rb.getString("Login existant"));
			}
			Global.reinitUserSessions();

			return prepareList();
		} catch (Exception e) {
			Logger.getLogger(UserController.class.getName()).log(Level.INFO,
					null, e);

			JsfUtil.addErrorMessage(e,
					mbfe_rb.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public String prepareEdit() {
		this.user = getItems().getRowData();
		return "Edit";
	}

	public String update() {
		// Modification
		ResourceBundle mbfe_rb = Bundle.MBFE.getBundle();
		try {
			if (this.user.getPassword().isEmpty()) {
				this.user.setPassword(ejbFacadeLocal.findById(
						this.user.getLogin()).getPassword());
			}
			this.user.setDroit(Global.getRightsManager().toLong(access));
			getEjbFacadeLocal().edit(this.user);
			this.user.setPassword("");

			JsfUtil.addSuccessMessage(mbfe_rb.getString("UtilisateurUpdated"));
			Global.reinitUserSessions();
			return "View";
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e,
					mbfe_rb.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public void destroy(ActionEvent event) {
		// Suppression
		this.user = getItems().getRowData();
		performDestroy();
		recreateModel();
		Global.reinitUserSessions();
	}

	private void performDestroy() {
		ResourceBundle mbfe_rb = Bundle.MBFE.getBundle();
		try {

			// Suppression de l'utilisateur
			getEjbFacadeLocal().remove(this.user);
			JsfUtil.addSuccessMessage(mbfe_rb.getString("UtilisateurDeleted"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e,
					mbfe_rb.getString("PersistenceErrorOccured"));
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,
					mbfe_rb.getString("PersistenceErrorOccured"), e);
		}
	}

	public DataModel<Siteuser> getItems() {
		if (items == null) {
			Siteuser u = findConnectedUser();
			if (u != null) {
				List<Siteuser> uList = new ArrayList<Siteuser>();

				for (Iterator<Siteuser> it = ejbFacadeLocal.findAll()
						.iterator(); it.hasNext();) {
					Siteuser utilisateur = it.next();
					if (utilisateur.has(u.getDroit(), Siteuser.INF)
							&& !utilisateur.is(u.getDroit())
							|| u.is(RightsManager.getInstance().toLong(
									Access.ROOT))) {

						uList.add(utilisateur);
					}
				}

				items = new ListDataModel<Siteuser>(uList);
			} else {
				items = new ListDataModel<Siteuser>();
			}
		}

		// Gestion des permaliens
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequestWrapper req = (HttpServletRequestWrapper) fc
				.getExternalContext().getRequest();
		String pseudoParam = req.getParameter("pseudo");

		if (pseudoParam != null) {
			int i = 0;
			for (Iterator<Siteuser> it = items.iterator(); it.hasNext();) {
				Siteuser utilisateur = it.next();
				if (utilisateur.getLogin().equals(pseudoParam)) {
					items.setRowIndex(i);
					break;
				}
				i++;
			}
		}

		return items;
	}

	public DataModel<Siteuser> getUserItems() {
		if (userItems == null) {
			ArrayList<Siteuser> userItemsList = new ArrayList<Siteuser>();
			for (Siteuser u : getItems()) {
				if (u.is(Global.getRightsManager().toLong(Access.USER))) {
					userItemsList.add(u);
				}
			}
			userItems = new ListDataModel<Siteuser>(userItemsList);
		}
		return userItems;
	}

	public void resetUserItems() {
		userItems = null;
	}

	public SelectItem[] getItemsAvailableSelectMany() {
		return JsfUtil.getSelectItems(ejbFacadeLocal.findAll(), false);
	}

	public SelectItem[] getItemsAvailableSelectOne() {
		return JsfUtil.getSelectItems(ejbFacadeLocal.findAll(), true);
	}

	@Override
	public void reinitialize() {
		userItems = null;
	}

	@FacesConverter(forClass = Siteuser.class)
	public static class UtilisateurControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			UserController controller = (UserController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"utilisateurController");
			return controller.getEjbFacadeLocal().findById(getKey(value));
		}

		java.lang.Long getKey(String value) {
			java.lang.Long key;
			key = Long.valueOf(value);
			return key;
		}

		String getStringKey(String string) {
			StringBuilder sb = new StringBuilder();
			sb.append(string);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Siteuser) {
				Siteuser o = (Siteuser) object;
				return getStringKey(o.getLogin());
			} else {
				throw new IllegalArgumentException("object " + object
						+ " is of type " + object.getClass().getName()
						+ "; expected type: " + UserController.class.getName());
			}
		}
	}

	public String connect() {
		Siteuser tmp = userCo;
		userCo = connection();
		if (userCo != null) {
			userCo.setPassword("connected");
			saveUserCoSession();

			if (userCo.is(Global.getRightsManager().toLong(Access.USER))) {
				return Nav.FApp.getDestination();
			} else {
				return Nav.FAppManage.getDestination();
			}
		} else {
			userCo = tmp;
			return null;
		}
	}

	private Siteuser connection() {
		ResourceBundle err_rb = Bundle.Err.getBundle();
		Siteuser u = null;
		try {
			u = ejbFacadeLocal.sOnePseudoPasswd(login, password);
			u.setConnected(true);

			login = null;
			password = null;
		} catch (EJBKException ex) {
			JsfUtil.addErrorMessage(err_rb.getString("noResultException_co"));

			return u;
		}

		ejbFacadeLocal.edit(u);
		return u;
	}

	private void disconnect() {
		Siteuser u = ejbFacadeLocal.findById(userCo.getLogin());
		u.setConnected(false);
		ejbFacadeLocal.edit(u);
	}

	private void saveUserCoSession() {
		Global.setSession(Session.UserCo, userCo);
	}

	public String deconnect() {
		Global.clearSessions();
		disconnect();
		userCo = new Siteuser();
		userCo.setDroit(Global.getRightsManager().toLong(Access.GUEST));
		userCo.setConnected(false);
		saveUserCoSession();
		return Nav.Co.getDestination();
	}

	public boolean rightTo(String rigth) {
		try {
			RightID r = RightID.valueOf(rigth);
			return getUserCo().has(Global.getRightsManager().toLong(r));
		} catch (IllegalArgumentException ex) {
			String message = "Error : IllegalArgumentException "
					+ "(RightID doesn't exists : '" + rigth + "')\n";
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,
					message);
		}

		return false;
	}

	public boolean accessTo(String access) {
		try {
			Access a = Access.valueOf(access);
			return getUserCo().has(Global.getRightsManager().toLong(a));
		} catch (IllegalArgumentException ex) {
			String message = "Error : IllegalArgumentException "
					+ "(Access doesn't exists : '" + access + "')\n";
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,
					message);
		}

		return false;
	}

	public boolean accessOnlyTo(String access) {
		try {
			Access a = Access.valueOf(access);
			return getUserCo().is(Global.getRightsManager().toLong(a));
		} catch (IllegalArgumentException ex) {
			String message = "Error : IllegalArgumentException "
					+ "(Access doesn't exists : '" + access + "')\n";
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,
					message);
		}

		return false;
	}

	public String showAccessForUser() {
		String result = "";
		Access[] accessfromrm = Global.getRightsManager().getAccess(
				getSelected().getDroit());
		for (int a = 0; a < accessfromrm.length; a++) {
			if (a == (accessfromrm.length - 1)) {
				result += accessfromrm[a].name();
			} else {
				result += accessfromrm[a].name() + ", ";
			}
		}
		return result;
	}

	public Siteuser findConnectedUser() {
		if (getUserCo() != null && getUserCo().getLogin() != null) {
			return ejbFacadeLocal.findById(getUserCo().getLogin());
		}
		return null;
	}
	
	public void laucnchTest(){
	}
}
