package org.metabosite.module.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.metabosite.module.controllers.user.UserController;
import org.metabosite.module.controllers.user.security.Page;
import org.metabosite.module.controllers.user.security.RightsManager;
import org.metabosite.module.utils.Bundle;


@ManagedBean(name = "global")
@ApplicationScoped
public class Global {

	private static RightsManager rightsManager = RightsManager.getInstance();
	private static Locale locale = null;
	public static final String TITLE = "KLM[e]";
	public static final String BUNDLES_BASE_NAME = "bundles.";
	private static String FILES_PATH;

	private static List<UserController> usersSessions = new ArrayList<UserController>();

	public static String getFILES_PATH() {
		return FILES_PATH;
	}

	public static RightsManager getRightsManager() {
		return rightsManager;
	}

	public String getTITLE() {
		return TITLE;
	}

	public static Locale getLocale() {
		if (FacesContext.getCurrentInstance() == null && locale == null) {
			locale = Locale.getDefault();
		} else if (locale == null) {
			locale = FacesContext.getCurrentInstance().getViewRoot()
					.getLocale();
		}
		return locale;
	}

	public static void setLocale(String languageCode) {
		locale = new Locale(languageCode);

		FacesContext.getCurrentInstance().getApplication()
				.setDefaultLocale(locale);

		// Redirection vers la page actuelle
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(contextRoot() + currentPage());
		} catch (IOException ex) {
			Logger.getLogger(Global.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	/** Creates a new instance of GlobalMB */
	public Global() {
		FILES_PATH = Bundle.Files.getBundle().getString("filesPath");
	}

	public static String contextRoot() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
	}

	public static String servletPath() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServletPath();
	}

	public static String linkFor(String path) {
		try {
			return contextRoot() + servletPath() + Page.valueOf(path).getPath();
		} catch (IllegalArgumentException ex) {
			return contextRoot() + servletPath() + path;
		}
	}

	public static String currentPage() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestPathInfo();
	}

	public static Long currentTime() {
		return System.currentTimeMillis();
	}

	public static Object getSession(Session session) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(session.getName());
	}

	public static void setSession(Session session, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(session.getName(), value);
	}

	public static void removeSession(Session session) {
		Map<String, Object> s = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		s.remove(session.getName());
	}

	public static void displaySessions() {
		Map<String, Object> s = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		for (Iterator<String> it = s.keySet().iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
	}

	public static void clearSessions() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
	}

	public static synchronized void addUserSession(UserController uC) {
		if (uC == null)
			return;
		if (!usersSessions.contains(uC))
			usersSessions.add(uC);
	}

	public static synchronized void removeUserSession(UserController uC) {
		if (uC == null)
			return;
		usersSessions.remove(uC);
	}

	public static synchronized void reinitUserSessions() {
		for (UserController uC : usersSessions) {
			Reinitialize rUC = (Reinitialize) uC;
			rUC.reinitialize();
		}
	}
}
