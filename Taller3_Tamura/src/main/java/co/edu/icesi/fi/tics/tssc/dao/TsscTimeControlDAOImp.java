package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Repository
public class TsscTimeControlDAOImp implements TsscTimeControlDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscTimecontrol tsscTimeControl) {
		entityManager.persist(tsscTimeControl);
	}

	@Override
	public void update(TsscTimecontrol tsscTimeControl) {
		entityManager.merge(tsscTimeControl);
	}

	@Override
	public void delete(TsscTimecontrol tsscTimeControl) {
		entityManager.remove(tsscTimeControl);		
	}

	@Override
	public TsscTimecontrol findById(long id) {
		return entityManager.find(TsscTimecontrol.class, id);
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "Select timecontrol FROM TsscTimecontrol timecontrol";
		return entityManager.createQuery(jpql).getResultList();
	}

}