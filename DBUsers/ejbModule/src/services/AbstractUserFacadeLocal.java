package src.services;

import java.util.List;

import javax.ejb.Local;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

@Local
public interface AbstractUserFacadeLocal<T> {

	public List<T> findAll();

	public boolean create(T entity) ;

	public void edit(T entity) ;

	public void remove(T entity) ;

	public T findById(Object id);

	public Boolean exist(T entity);
}
