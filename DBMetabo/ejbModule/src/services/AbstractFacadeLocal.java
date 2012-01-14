package src.services;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AbstractFacadeLocal<T> {

	public List<T> findAll();

	public void create(T entity);

	public void edit(T entity);

	public void remove(T entity);

	public T findById(Object id);

	public Boolean exist(T entity);
}
