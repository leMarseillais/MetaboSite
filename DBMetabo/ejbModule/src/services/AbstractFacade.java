package src.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Session Bean implementation class AbstractFacade
 * 
 * @param <T>
 */
@Stateless
public abstract class AbstractMetaboFacade<T> implements
		AbstractMetaboFacadeLocal<T> {

	private Class<T> entityClass;

	protected abstract EntityManager getEntityManager();

	public AbstractFacade(Class<T> entityClass) {
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

	@Override
	public Boolean exist(T entity) {
		return getEntityManager().contains(entity);
	}

}
