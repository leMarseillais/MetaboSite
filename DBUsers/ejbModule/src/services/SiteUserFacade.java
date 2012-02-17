package src.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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
	
	private UserTransaction utx;
	
	/**
	 * @see AbstractFacade#AbstractFacade()
	 */
	public SiteUserFacade() {
		super(ENTITY_CLASS);
		Context context;
		try {
			context = new InitialContext();
			utx = (UserTransaction) context.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public boolean create(Siteuser entity) {
		Boolean work = false;
		Boolean test = true;
//		try {
//			utx.begin();
			if (entityManager.find(Siteuser.class, entity.getLogin()) == null) {
				getEntityManager().persist(entity);
				work = true;
			}
//			utx.commit();
//		} catch (NotSupportedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SystemException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (RollbackException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicMixedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicRollbackException e) {
//			test = false;
//			e.printStackTrace();
//		}
//		if (!test) {
//			try {
//				utx.setRollbackOnly();
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return work;
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
//		Boolean test = true;
//		try {
//			utx.begin();
			getEntityManager().merge(entity);
//			utx.commit();
//		} catch (NotSupportedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SystemException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (RollbackException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicMixedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicRollbackException e) {
//			test = false;
//			e.printStackTrace();
//		}
//		if (!test) {
//			try {
//				utx.setRollbackOnly();
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

	@Override
	public void remove(Siteuser entity){
//		Boolean test = true;
//		try {
//		utx.begin();
		getEntityManager().remove(getEntityManager().merge(entity));
//		utx.commit();
//		} catch (NotSupportedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SystemException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (RollbackException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicMixedException e) {
//			test = false;
//			e.printStackTrace();
//		} catch (HeuristicRollbackException e) {
//			test = false;
//			e.printStackTrace();
//		}
//		if (!test) {
//			try {
//				utx.setRollbackOnly();
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
