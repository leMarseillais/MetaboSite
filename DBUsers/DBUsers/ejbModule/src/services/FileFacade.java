package src.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entities.File;

/**
 * Session Bean implementation class FileFacade
 */
@Stateless
public class FileFacade extends AbstractFacade<File> implements FileFacadeLocal {
  
	@PersistenceContext(unitName = "DBUsers")
	private EntityManager entityManager;
	
	private static final Class<File> ENTITY_CLASS = File.class;  
    /**
     * @see AbstractFacade#AbstractFacade()
     */
    public FileFacade() {
        super(ENTITY_CLASS);
    }
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
