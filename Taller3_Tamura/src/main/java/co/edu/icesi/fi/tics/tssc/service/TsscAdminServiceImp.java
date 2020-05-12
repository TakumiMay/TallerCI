package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService {

	private TsscAdminRepository tsscAdminRepository;
	
	@Autowired
	public TsscAdminServiceImp(TsscAdminRepository tsscAdminRepository) {
		this.tsscAdminRepository = tsscAdminRepository;
	}
	@Override
	public TsscAdmin saveAdmin(TsscAdmin tsscAdmin) {
		return tsscAdminRepository.save(tsscAdmin);
	}

	@Override
	public TsscAdmin editAdmin(TsscAdmin tsscAdmin) {
		return tsscAdminRepository.save(tsscAdmin);
	}

	@Override
	public void deleteAdmin(TsscAdmin tsscAdmin) {
		tsscAdminRepository.delete(tsscAdmin);
	}

}