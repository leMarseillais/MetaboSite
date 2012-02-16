package src.services;
import javax.ejb.Local;

import src.EJBKException;
import src.entities.Siteuser;

@Local
public interface SiteUserFacadeLocal extends AbstractUserFacadeLocal<Siteuser> {
	
	public Siteuser oneLogin(String login);
	
	public Siteuser oneLoginConnected(String login);
	
	public Boolean isConnected(String login);
	
	public Boolean takenLogin(String login);

	public Siteuser sOnePseudoPasswd(String login, String password) throws EJBKException;

	public Siteuser sOnePseudo(String pseudo) throws EJBKException;
}
