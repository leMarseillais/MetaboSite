package src.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entities.Metabodatabase;

/**
 * Session Bean implementation class MetabodatabaseFacade
 */
@Stateless
@LocalBean
public class MetabodatabaseFacade extends AbstractFacade<Metabodatabase>
		implements MetabodatabaseFacadeLocal {

	@PersistenceContext(unitName = "DBMetabo")
	private EntityManager entityManager;

	private static Class<Metabodatabase> ENTITY_ClASS = Metabodatabase.class;

	public MetabodatabaseFacade() {
		super(ENTITY_ClASS);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Metabodatabase> findByKeyword(String keyword) {
		List<Metabodatabase> all = findAll();
		ArrayList<Metabodatabase> answer = new ArrayList<Metabodatabase>();
		for (Metabodatabase metabodatabase : all) {
			if (metabodatabase.getKeyword().contains(keyword)) {
				answer.add(metabodatabase);
			}
		}
		return answer;
	}
}
