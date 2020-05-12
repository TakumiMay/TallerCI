package co.edu.icesi.fi.tics.tssc.test.unit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImp;
import lombok.extern.java.Log;

@Log
class TsscGameTest {
	
	@InjectMocks
	TsscGameServiceImp tsscGameServiceImp;
	
	@Mock
	TsscGameRepository tsscGameRepository;
	
	TsscGame game;
	
	@BeforeAll
	public void setUp() {
		log.info("-----> SETUP <-----");
		MockitoAnnotations.initMocks(this);
		
		game = new TsscGame();
	}
	
	@Test
	public void testSaveGame() {
		
	}
	
	@Test
	public void testEditGame() {
		
	}
	
	@AfterAll
	public static void afterTest() {
		log.info("-----> DESTROY <-----");
	}
	
}