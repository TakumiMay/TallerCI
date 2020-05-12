package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface TsscGameService {

	public TsscGame saveGame(TsscGame tsscGame);
	
	public TsscGame editGame(TsscGame tsscGame);
	
	public void deleteGame(TsscGame tsscGame);
	
}