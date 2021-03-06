package src.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
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
	abstract public boolean create(T entity) ;

	@Override
	abstract public void edit(T entity) ;

	@Override
	public abstract void remove(T entity);

	@Override
	public Boolean exist(T entity) {
		return getEntityManager().contains(entity);
	}

}
