package src.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import src.entities.Siteuser;

/**
 * Session Bean implementation class SiteUserFacade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SiteUserFacade extends AbstractFacade<Siteuser> implements SiteUserFacadeLocal {
       
	private static final Class<Siteuser> ENTITY_CLASS = Siteuser.class;
	
    /**
     * @see AbstractFacade#AbstractFacade()
     */
    public SiteUserFacade() {
        super(ENTITY_CLASS);
        // TODO Auto-generated constructor stub
    }

}
