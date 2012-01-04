package src.services;

import javax.ejb.Stateless;

import src.entities.Project;

/**
 * Session Bean implementation class ProjectFacade
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> implements ProjectFacadeLocal {
      
	private static final Class<Project> ENTITY_CLASS = Project.class;
	
    /**
     * @see AbstractFacade#AbstractFacade()
     */
    public ProjectFacade() {
        super(ENTITY_CLASS);
        // TODO Auto-generated constructor stub
    }

}
