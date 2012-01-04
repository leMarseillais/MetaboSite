package src.services;
import javax.ejb.Local;

import src.entities.Siteuser;

@Local
public interface SiteUserFacadeLocal extends AbstractFacadeLocal<Siteuser> {
	
	public Siteuser oneLogin(String login);
	
	public Siteuser oneLoginConnected(String login);
	
	public Boolean isConnected(String login);
	
	public Boolean takenLogin(String login);
}
