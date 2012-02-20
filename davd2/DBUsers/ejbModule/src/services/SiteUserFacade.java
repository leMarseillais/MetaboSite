package src.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.weld.bean.builtin.AbstractFacade;

import src.EJBKException;
import src.entities.Siteuser;

/**
 * Session Bean implementation class SiteUserFacade
 */
@Stateless
public class SiteUserFacade extends AbstractUserFacade<Siteuser> implements
		SiteUserFacadeLocal {

	@PersistenceContext(unitName = "DBUsers")
	private EntityManager entityManager;

	private static final Class<Siteuser> ENTITY_CLASS = Siteuser.class;
	
	/**
	 * @see AbstractFacade#AbstractFacade()
	 */
	public SiteUserFacade() {
		super(ENTITY_CLASS);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Siteuser oneLogin(String login) {
		if (login == null || login.isEmpty()) {
			return null;
		} else {
			Siteuser siteuser = null;
			TypedQuery<Siteuser> query = entityManager.createNamedQuery(
					"OneLogin", Siteuser.class).setParameter("login", login);
			try {
				siteuser = query.getSingleResult();
			} catch (NoResultException e) {
				// TODO: handle exception
			}
			return siteuser;
		}
	}

	@Override
	public Siteuser oneLoginConnected(String login) {
		if (login == null || login.isEmpty()) {
			return null;
		} else {
			Siteuser siteuser = null;
			TypedQuery<Siteuser> query = entityManager.createNamedQuery(
					"OneLoginConnected", Siteuser.class).setParameter("login",
					login);
			try {
				siteuser = query.getSingleResult();
			} catch (NoResultException e) {
				// TODO: handle exception
			}
			return siteuser;
		}
	}

	@Override
	public Boolean isConnected(String login) {
		try {
			this.oneLoginConnected(login);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean takenLogin(String login) {
		try {
			this.oneLogin(login);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Siteuser sOnePseudoPasswd(String login, String password)
			throws EJBKException {
		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			return null;
		} else {
			Siteuser u = null;

			TypedQuery<Siteuser> tq = entityManager
					.createNamedQuery("OnePseudoPasswd", Siteuser.class)
					.setParameter("login", login)
					.setParameter("password", password);

			try {
				u = tq.getSingleResult();
			} catch (NoResultException ex) {
				throw new EJBKException(ex.getLocalizedMessage());
			}

			return u;
		}
	}

	@Override
	public Siteuser sOnePseudo(String pseudo) throws EJBKException {
		if (pseudo == null || pseudo.isEmpty()) {
			return null;
		} else {
			Siteuser u = null;

			TypedQuery<Siteuser> tq = getEntityManager().createNamedQuery(
					"OnePseudo", Siteuser.class).setParameter("pseudo", pseudo);

			try {
				u = tq.getSingleResult();
			} catch (NoResultException ex) {
				throw new EJBKException(ex.getLocalizedMessage());
			}

			return u;
		}
	}

	@Override
	public void edit(Siteuser entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public void remove(Siteuser entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}

}
