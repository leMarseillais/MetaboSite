package src.services;
import javax.ejb.Local;

import src.entities.File;

@Local
public interface FileFacadeLocal extends AbstractFacadeLocal<File> {

}
