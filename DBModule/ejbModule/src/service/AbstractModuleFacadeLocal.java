package src.service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AbstractModuleFacadeLocal<T> {

	public List<T> findAll();

	public void create(T entity);

	public void edit(T entity);

	public void remove(T entity);
}
