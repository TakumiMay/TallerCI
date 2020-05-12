package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
public class TsscAdminDAOImp implements TsscAdminDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscAdmin tsscAdmin) {
		entityManager.persist(tsscAdmin);		
	}

	@Override
	public void update(TsscAdmin tsscAdmin) {
		entityManager.merge(tsscAdmin);
	}

	@Override
	public void delete(TsscAdmin tsscAdmin) {
		entityManager.remove(tsscAdmin);
	}

	@Override
	public TsscAdmin findById(long id) {
		return entityManager.find(TsscAdmin.class, id);
	}

	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select admin FROM TsscAdmin admin";
		return entityManager.createQuery(jpql).getResultList();
	}
	
}