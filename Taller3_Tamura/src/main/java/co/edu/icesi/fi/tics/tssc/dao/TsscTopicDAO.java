package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicDAO {

	public void save(TsscTopic tsscTopic);
	public void update(TsscTopic tsscTopic);
	public void delete(TsscTopic tsscTopic);
	public TsscTopic findById(long id);
	public List<TsscTopic> findAll();
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	public List<TsscTopic> findByGameDate(LocalDate scheduledDate);	

}