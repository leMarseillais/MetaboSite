package src.services;
import javax.ejb.Local;

import src.EJBKException;
import src.entities.Files;

@Local
public interface FileFacadeLocal extends AbstractUserFacadeLocal<Files> {

	public Files sOneChemin(String path) throws EJBKException;

	public boolean takenChemin(String chemin);
}
