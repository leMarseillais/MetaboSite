package src.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import src.entities.File;

/**
 * Session Bean implementation class FileFacade
 */
@Stateless
public class FileFacade extends AbstractFacade<File> implements FileFacadeLocal {
  
	private static final Class<File> ENTITY_CLASS = File.class;  
    /**
     * @see AbstractFacade#AbstractFacade()
     */
    public FileFacade() {
    	
        super(ENTITY_CLASS);
        // TODO Auto-generated constructor stub
    }
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
