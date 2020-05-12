package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminDAO {

	public void save(TsscAdmin tsscAdmin);
	public void update(TsscAdmin tsscAdmin);
	public void delete(TsscAdmin tsscAdmin);
	public TsscAdmin findById(long id);
	public List<TsscAdmin> findAll();
	
}