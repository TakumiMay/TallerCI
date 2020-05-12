package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicService {

	public TsscTopic saveTopic(TsscTopic tsscTopic);
	
	public TsscTopic editTopic(TsscTopic tsscTopic);
	
	public void deleteTopic(TsscTopic tsscTopic);
	
}