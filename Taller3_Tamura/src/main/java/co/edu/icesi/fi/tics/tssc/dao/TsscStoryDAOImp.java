package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
public class TsscStoryDAOImp implements TsscStoryDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscStory tsscStory) {
		entityManager.persist(tsscStory);
	}

	@Override
	public void update(TsscStory tsscStory) {
		entityManager.merge(tsscStory);
	}

	@Override
	public void delete(TsscStory tsscStory) {
		entityManager.remove(tsscStory);
	}

	@Override
	public TsscStory findById(long id) {
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "Select story FROM TsscStory story";
		return entityManager.createQuery(jpql).getResultList();
	}

}