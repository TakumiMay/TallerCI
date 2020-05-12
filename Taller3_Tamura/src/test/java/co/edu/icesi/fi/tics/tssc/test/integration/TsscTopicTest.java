package co.edu.icesi.fi.tics.tssc.test.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;
import lombok.extern.java.Log;

@Log
class TsscTopicTest {

	@Autowired
	TsscTopicServiceImp tsscTopicServiceImp;
	
	TsscTopic tsscTopic0;
	
	@BeforeAll
	public void setUp() {
		log.info("-----> SETUP <-----");		
		tsscTopic0 = new TsscTopic();
	}

	@Test
	public void testSaveTopicWithInvalidSprintsNGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(-50L);
		tsscTopic0.setDefaultSprints(-50L);
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.saveTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}

	@Test
	public void testSaveTopicWithValidSprintsNGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(50L);
		tsscTopic0.setDefaultSprints(50L);
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.saveTopic(tsscTopic0);
		//Then
		assertNotNull(tsscTopic0_2);
		assertTrue(tsscTopic0_2.getDefaultGroups() > 0L);
		assertTrue(tsscTopic0_2.getDefaultSprints() > 0L);
	}
	
	@Test
	public void testSaveTopicWithInvalidSprints() {
		//Given	
		tsscTopic0.setDefaultGroups(50L);
		tsscTopic0.setDefaultSprints(-50L);
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.saveTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}
	
	@Test
	public void testSaveTopicWithInvalidGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(-50L);
		tsscTopic0.setDefaultSprints(50L);
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.saveTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}

	@Test
	public void testEditTopicWithInvalidSprintsNGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(-50L);
		tsscTopic0.setDefaultSprints(-50L);
				
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.editTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}

	@Test
	public void testEditTopicWithValidSprintsNGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(50L);
		tsscTopic0.setDefaultSprints(50L);
		
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.editTopic(tsscTopic0);
		//Then
		assertNotNull(tsscTopic0_2);
		assertTrue(tsscTopic0_2.getDefaultGroups() > 0L);
		assertTrue(tsscTopic0_2.getDefaultSprints() > 0L);
	}
	
	@Test
	public void testEditTopicWithInvalidSprints() {
		//Given	
		tsscTopic0.setDefaultGroups(50L);
		tsscTopic0.setDefaultSprints(-50L);
		
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.editTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}
	
	@Test
	public void testEditTopicWithInvalidGroups() {
		//Given	
		tsscTopic0.setDefaultGroups(-50L);
		tsscTopic0.setDefaultSprints(50L);
		
		//When
		TsscTopic tsscTopic0_2 = tsscTopicServiceImp.editTopic(tsscTopic0);
		//Then
		assertNull(tsscTopic0_2);
	}

	@AfterAll
	public static void afterTest() {
		log.info("-----> DESTROY <-----");
	}
	
}