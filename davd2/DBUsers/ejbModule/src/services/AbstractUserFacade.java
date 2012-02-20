package src.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Session Bean implementation class AbstractFacade
 * 
 * @param <T>
 */
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
	public Boolean exist(T entity) {
		return getEntityManager().contains(entity);
	}

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

}
