package co.edu.icesi.fi.tics.tssc.test.unit.DAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.TsscGameDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscGameDAOTest extends TestCase {

	@Autowired
	TsscGameDAO gameDAO;
	
	@BeforeEach
	public void setUp() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setAdminPassword("1234");
		tsscGame.setGuestPassword("1234");
		tsscGame.setName("Sudoku");
		tsscGame.setScheduledDate(LocalDate.now());
		tsscGame.setScheduledTime(LocalTime.now());
		tsscGame.setUserPassword("1234");
		
		gameDAO.save(tsscGame);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveGame() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setAdminPassword("5678");
		tsscGame.setGuestPassword("5678");
		tsscGame.setName("SushiGoParty");
		tsscGame.setScheduledDate(LocalDate.now());
		tsscGame.setScheduledTime(LocalTime.now());
		tsscGame.setUserPassword("5678");
		
		gameDAO.save(tsscGame);
		
		assertNotNull(gameDAO.findById(tsscGame.getId()));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateGame() {
		TsscGame tsscGame = gameDAO.findById(1);
		String newName = "SushiGoParty";
		tsscGame.setName(newName);
		
		gameDAO.update(tsscGame);
		
		assertEquals(newName, gameDAO.findById(1).getName());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteGame() {
		
		//gameDAO.delete(tsscGame);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameById() {
		
		//gameDAO.findById(id);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllGames() {
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findAll();
		
//		System.out.println();
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByName() {
	
		//gameDAO.findByName(name);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByDescription() {
		
		//gameDAO.findByDescription(description);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByTopicId() {
		
		//gameDAO.findByTopicId(id);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByDateRange() {
		
		//gameDAO.findByDateRange(scheduledDate, scheduledDate2);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByDateAndTimeRange() {
		
		//gameDAO.findByDateAndTimeRange(scheduledDate, localTime, localTime2);
	}
	
}