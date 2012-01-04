package src.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import src.entities.Siteuser;

/**
 * Session Bean implementation class SiteUserFacade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SiteUserFacade extends AbstractFacade<Siteuser> implements
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
					"OneLoginConnected", Siteuser.class).setParameter("login", login);
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

}
