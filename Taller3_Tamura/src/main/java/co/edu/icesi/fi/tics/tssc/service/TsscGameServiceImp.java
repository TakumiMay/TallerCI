package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;

@Service
public class TsscGameServiceImp implements TsscGameService {

	private TsscGameRepository tsscGameRepository;
	
	@Autowired
	public TsscGameServiceImp(TsscGameRepository tsscGameRepository) {
		this.tsscGameRepository = tsscGameRepository;
	}

	@Override
	public TsscGame saveGame(TsscGame tsscGame) {
		if(tsscGame.getNGroups() <= 0 || tsscGame.getNSprints() <= 0) 
			return null;
		
		return tsscGameRepository.save(tsscGame);
	}

	@Override
	public TsscGame editGame(TsscGame tsscGame) {
		if(tsscGameRepository.findById(tsscGame.getId()) == null)
			return null;
		if(tsscGame.getNGroups() <= 0 || tsscGame.getNSprints() <= 0) 
			return null;
		
		return tsscGameRepository.save(tsscGame);
	}
	
	public Iterable<TsscGame> findAll() {
		return tsscGameRepository.findAll();
	}
	
	public TsscGame findById(long id) {
		return tsscGameRepository.findById(id).get();
	}
	
	@Override
	public void deleteGame(TsscGame tsscGame) {
		tsscGameRepository.delete(tsscGame);
	}
}