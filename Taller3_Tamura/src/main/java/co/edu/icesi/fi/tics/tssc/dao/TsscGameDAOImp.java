package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
public class TsscGameDAOImp implements TsscGameDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscGame tsscGame) {
		entityManager.persist(tsscGame);
	}

	@Override
	public void update(TsscGame tsscGame) {
		entityManager.merge(tsscGame);
	}

	@Override
	public void delete(TsscGame tsscGame) {
		entityManager.remove(tsscGame);
	}

	@Override
	public TsscGame findById(long id) {
		return entityManager.find(TsscGame.class, id);
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select game FROM TsscGame game";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByName(String name) {
		String jpql = "Select game FROM TsscGame game WHERE game.name="+name;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicDescription(String description) {
		String jpql = "Select game FROM TsscGame game WHERE game.tsscTopic.description="+description;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicId(long id) {
		String jpql = "Select game FROM TsscGame game WHERE game.tsscTopic.id="+id;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate scheduledDate, LocalDate scheduledDate2) {
		String jpql = "Select game FROM TsscGame game WHERE game.scheduledDate BETWEEN "+scheduledDate+" AND "+scheduledDate2;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate scheduledDate, LocalTime localTime, LocalTime localTime2) {
		String jpql = ("Select game FROM TsscGame game WHERE game.scheduledDate ="+scheduledDate+" AND game.localTime BETWEEN "+localTime+" AND "+localTime2);
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Object[]> findByDateAndReturnTopics(LocalDate scheduledDate) {
		String jpql = "Select game.tsscTopic, game FROM TsscGame game WHERE game.scheduledDate=:date ORDER BY game.scheduledTime ASC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", scheduledDate);
		return query.getResultList();
	}

}