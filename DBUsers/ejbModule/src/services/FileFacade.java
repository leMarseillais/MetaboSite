package src.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
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
import src.entities.Files;

/**
 * Session Bean implementation class FileFacade
 */
@Stateless
public class FileFacade extends AbstractUserFacade<Files> implements
		FileFacadeLocal {

	@PersistenceContext(unitName = "DBUsers")
	private EntityManager entityManager;

	private static final Class<Files> ENTITY_CLASS = Files.class;
	
	private UserTransaction utx;

	/**
	 * @see AbstractFacade#AbstractFacade()
	 */
	public FileFacade() {
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
	public boolean create(Files entity) {
		Boolean work = false;
		Boolean test = true;
//		try {
//			utx.begin();

			Logger.getLogger(AbstractUserFacade.class.getName()).log(
					Level.INFO, entity.toString());
			if (entityManager.find(Files.class, entity.getFileLocation()) == null) {
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
	public Files sOneChemin(String chemin) throws EJBKException {
		if (chemin == null || chemin.isEmpty()) {
			return null;
		} else {
			Files f = null;

			TypedQuery<Files> tq = entityManager.createNamedQuery("OneChemin",
					Files.class).setParameter("chemin", chemin);

			try {
				f = tq.getSingleResult();
			} catch (NoResultException ex) {
				throw new EJBKException(ex.getLocalizedMessage());
			}

			return f;
		}
	}

	public boolean takenChemin(String chemin) {
		try {
			sOneChemin(chemin);
			return true;
		} catch (EJBKException ex) {
			return false;
		}
	}

	@Override
	public void edit(Files entity) {
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
	public void remove(Files entity) {
//		Boolean test = true;
//		try {
//			utx.begin();
			getEntityManager().remove(getEntityManager().merge(entity));
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
}
