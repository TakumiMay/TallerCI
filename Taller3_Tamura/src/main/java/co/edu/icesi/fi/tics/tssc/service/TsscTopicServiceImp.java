package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;

@Service
public class TsscTopicServiceImp implements TsscTopicService {

	private TsscTopicRepository tsscTopicRepository;

	@Autowired
	public TsscTopicServiceImp(TsscTopicRepository tsscTopicRepository) {
		this.tsscTopicRepository = tsscTopicRepository;
	}

	@Override
	public TsscTopic saveTopic(TsscTopic tsscTopic) {
		if (tsscTopic.getDefaultGroups() <= 0 || tsscTopic.getDefaultSprints() <= 0)
			return null;

		return tsscTopicRepository.save(tsscTopic);
	}

	@Override
	public TsscTopic editTopic(TsscTopic tsscTopic) {
		if(tsscTopicRepository.findById(tsscTopic.getId()) == null)
			return null;
			
		if (tsscTopic.getDefaultGroups() <= 0 || tsscTopic.getDefaultSprints() <= 0)
			return null;

		return tsscTopicRepository.save(tsscTopic);
	}
	
	public Iterable<TsscTopic> findAll() {
		return tsscTopicRepository.findAll();
	}

	public TsscTopic findById(long id) {
		return tsscTopicRepository.findById(id).get();
	}
	
	@Override
	public void deleteTopic(TsscTopic tsscTopic) {
		tsscTopicRepository.delete(tsscTopic);
	}
	
}