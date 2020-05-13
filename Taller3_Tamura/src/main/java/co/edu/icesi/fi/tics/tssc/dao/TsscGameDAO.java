package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TsscGameDAO {

	public void save(TsscGame tsscGame);
	public void update(TsscGame tsscGame);
	public void delete(TsscGame tsscGame);
	public TsscGame findById(long id);
	public List<TsscGame> findAll();
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByTopicDescription(String description);
	public List<TsscGame> findByTopicId(long id);
	public List<TsscGame> findByDateRange(LocalDate scheduledDate, LocalDate scheduledDate2);
	public List<TsscGame> findByDateAndTimeRange(LocalDate scheduledDate, LocalTime localTime, LocalTime localTime2);
	public List<Object[]> findByDateAndReturnTopics(LocalDate scheduledDate);
	
}