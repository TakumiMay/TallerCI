package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryDAO {

	public void save(TsscStory tsscStory);
	public void update(TsscStory tsscStory);
	public void delete(TsscStory tsscStory);
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
	
}