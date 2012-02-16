package src.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


/**
 * Session Bean implementation class AbstractFacade
 * @param <T>
 */
@Stateless
public abstract class AbstractModuleFacade<T> implements AbstractModuleFacadeLocal<T> {

	private Class<T> entityClass;
	
	protected abstract EntityManager getEntityManager();
	
	public AbstractModuleFacade(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery query =  getEntityManager().getCriteriaBuilder().createQuery();
		query.select(query.from(entityClass));
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}


}
