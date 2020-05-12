package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public List<TsscGame> findByDescription(String description) {
		String jpql = "Select game FROM TsscGame game WHERE game.description="+description;
		return entityManager.createQuery(jpql).getResultList();
	}
//MALLL
	@Override
	public List<TsscGame> findByTopicId(long id) {
		String jpql = "Select game FROM TsscGame game WHERE game.tsscTopic="+entityManager.find(TsscTopic.class, id);
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate scheduledDate, LocalDate scheduledDate2) {
		String jpql = "Select game FROM TsscGame game WHERE game.scheduledDate >="+scheduledDate+" OR game.scheduledDate <="+scheduledDate2;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate scheduledDate, LocalTime localTime, LocalTime localTime2) {
		String jpql = ("Select game FROM TsscGame game WHERE game.scheduledDate ="+scheduledDate+" AND (game.localTime >="+localTime+"OR game.localTime <="+localTime2+")");
		return entityManager.createQuery(jpql).getResultList();
	}

}