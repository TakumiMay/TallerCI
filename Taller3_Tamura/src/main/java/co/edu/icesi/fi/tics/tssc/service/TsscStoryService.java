package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryService {

	public TsscStory saveStory(TsscStory tsscStory);
	
	public TsscStory editStory(TsscStory tsscStory);
	
	public void deleteStory(TsscStory tsscStory);

}