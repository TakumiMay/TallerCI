package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
public class TsscTopicDAOImp implements TsscTopicDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTopic tsscTopic) {
		entityManager.persist(tsscTopic);
	}

	@Override
	public void update(TsscTopic tsscTopic) {
		entityManager.merge(tsscTopic);
	}

	@Override
	public void delete(TsscTopic tsscTopic) {
		entityManager.remove(tsscTopic);
	}

	@Override
	public TsscTopic findById(long id) {
		return entityManager.find(TsscTopic.class, id);
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select topic FROM TsscTopic topic";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "Select topic FROM TsscTopic topic WHERE topic.name="+name;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String jpql = "Select topic FROM TsscTopic topic WHERE topic.description="+description;
		return entityManager.createQuery(jpql).getResultList();
	}

}