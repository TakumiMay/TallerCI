package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;

@Service
public class TsscStoryServiceImp implements TsscStoryService {

	private TsscStoryRepository tsscStoryRepository;
	
	@Autowired
	public TsscStoryServiceImp(TsscStoryRepository tsscStoryRepository) {
		this.tsscStoryRepository = tsscStoryRepository;
	}

	@Override
	public TsscStory saveStory(TsscStory tsscStory) {
		if(tsscStory.getBusinessValue().intValue() <=0 || tsscStory.getInitialSprint().intValue() <= 0 || tsscStory.getPriority().intValue() <=0) 
			return null;
		
		return tsscStoryRepository.save(tsscStory);
	}

	@Override
	public TsscStory editStory(TsscStory tsscStory) {
		if(tsscStoryRepository.findById(tsscStory.getId()) == null)
			return null;
		if(tsscStory.getBusinessValue().intValue() <=0 || tsscStory.getInitialSprint().intValue() <= 0 || tsscStory.getPriority().intValue() <=0) 
			return null;		
		
		return tsscStoryRepository.save(tsscStory);
	}
	
	public Iterable<TsscStory> findAll(){
		return tsscStoryRepository.findAll();
	}
	
	public TsscStory findById(long id) {
		return tsscStoryRepository.findById(id).get();
	}
	@Override
	public void deleteStory(TsscStory tsscStory) {
		tsscStoryRepository.delete(tsscStory);
	}
}