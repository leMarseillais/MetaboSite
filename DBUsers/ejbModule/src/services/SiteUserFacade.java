package src.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.jboss.weld.bean.builtin.AbstractFacade;

import src.EJBKException;
import src.entities.Siteuser;

/**
 * Session Bean implementation class SiteUserFacade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
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
	public boolean create(Siteuser entity) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		Boolean work = false;

		utx.begin();

		Logger.getLogger(AbstractUserFacade.class.getName()).log(Level.INFO,
				entity.toString());
		if (entityManager.find(Siteuser.class, entity.getLogin()) == null) {
			getEntityManager().persist(entity);
			work = true;
		}
		utx.commit();
		return work;
	}
	
	@Override
    public Siteuser sOnePseudo(String pseudo) throws EJBKException {
        if (pseudo == null || pseudo.isEmpty()) {
            return null;
        } else {
            Siteuser u = null;
            
            TypedQuery<Siteuser> tq = getEntityManager()
                    .createNamedQuery("OnePseudo", Siteuser.class)
                    .setParameter("pseudo", pseudo);
            
            try {
                u = tq.getSingleResult();
            } catch (NoResultException ex) {
                throw new EJBKException(ex.getLocalizedMessage());
            }
            
            return u;
        }
    }

	@Override
	public void edit(Siteuser entity) throws NotSupportedException,
			SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException,
			HeuristicRollbackException {
		utx.begin();
		getEntityManager().merge(entity);		
		utx.commit();
		
	}

	@Override
	public void remove(Siteuser entity) throws NotSupportedException,
			SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException,
			HeuristicRollbackException {
		utx.begin();
		getEntityManager().remove(getEntityManager().merge(entity));
		utx.commit();
		
	}

}
