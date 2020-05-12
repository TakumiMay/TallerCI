package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface TsscTimeControlDAO {

	public void save(TsscTimecontrol tsscTimeControl);
	public void update(TsscTimecontrol tsscTimeControl);
	public void delete(TsscTimecontrol tsscTimeControl);
	public TsscTimecontrol findById(long id);
	public List<TsscTimecontrol> findAll();
	
}