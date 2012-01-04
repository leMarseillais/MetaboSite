package src.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entities.Project;

/**
 * Session Bean implementation class ProjectFacade
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> implements ProjectFacadeLocal {
      
	@PersistenceContext(unitName = "DBUsers")
	private EntityManager entityManager;
	
	private static final Class<Project> ENTITY_CLASS = Project.class;
	
    /**
     * @see AbstractFacade#AbstractFacade()
     */
    public ProjectFacade() {
        super(ENTITY_CLASS);
    }

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
