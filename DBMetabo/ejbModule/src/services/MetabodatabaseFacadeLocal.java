package src.services;

import java.util.List;

import javax.ejb.Local;

import src.entities.Metabodatabase;

@Local
public interface MetabodatabaseFacadeLocal extends
		AbstractFacadeLocal<Metabodatabase> {

	public List<Metabodatabase> findByKeyword(String keyword);
}
