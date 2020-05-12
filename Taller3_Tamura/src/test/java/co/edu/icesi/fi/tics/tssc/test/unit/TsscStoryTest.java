package co.edu.icesi.fi.tics.tssc.test.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryServiceImp;
import lombok.extern.java.Log;

@Log
class TsscStoryTest {

	@InjectMocks
	TsscStoryServiceImp tsscStoryServiceImp;

	@Mock
	TsscStoryRepository tsscStoryRepository;

	@BeforeAll
	public void setUp() {
		log.info("-----> SETUP <-----");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveStory() {
		//Given 
		TsscStory story = new TsscStory();
		
		//When 
		story = tsscStoryServiceImp.saveStory(story);
		
		//Then
		assertTrue(story.getInitialSprint().intValue()>0);
		assertTrue(story.getBusinessValue().intValue()>0);
		assertTrue(story.getPriority().intValue()>0);
	}

	@Test
	public void testEditStory() {

	}

}