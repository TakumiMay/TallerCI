package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminService {

	public TsscAdmin saveAdmin(TsscAdmin tsscAdmin);
	
	public TsscAdmin editAdmin(TsscAdmin tsscAdmin);
	
	public void deleteAdmin(TsscAdmin tsscAdmin);
	
}