package src.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Session Bean implementation class AbstractFacade
 * 
 * @param <T>
 */
@Stateless
public abstract class AbstractUserFacade<T> implements
		AbstractUserFacadeLocal<T> {

	private Class<T> entityClass;

	protected abstract EntityManager getEntityManager();

	@Resource
	protected UserTransaction utx;

	public AbstractUserFacade(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery query = getEntityManager().getCriteriaBuilder()
				.createQuery();
		query.select(query.from(entityClass));
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public T findById(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	abstract public boolean create(T entity) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException ;

	@Override
	abstract public void edit(T entity) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException ;

	@Override
	public abstract void remove(T entity) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException;

	@Override
	public Boolean exist(T entity) {
		return getEntityManager().contains(entity);
	}

}
