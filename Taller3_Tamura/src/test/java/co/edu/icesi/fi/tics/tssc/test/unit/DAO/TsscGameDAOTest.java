package co.edu.icesi.fi.tics.tssc.test.unit.DAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import co.edu.icesi.fi.tics.tssc.dao.TsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscGameDAOTest extends TestCase {

	@Autowired
	private TsscGameDAO gameDAO;
	@Autowired
	TsscTopicDAO topicDAO;
	
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
		
		assertNotNull(gameDAO.findById(2));
		assertNotNull(gameDAO.findByName("SushiGoParty"));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateGame() {
		TsscGame tsscGame = gameDAO.findById(1);
		String newName = "Sudoku 2.0";
		tsscGame.setName(newName);
		
		gameDAO.update(tsscGame);
		
		assertEquals(newName, gameDAO.findById(1).getName());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteGame() {
		TsscGame tsscGame = gameDAO.findById(1);
		
		gameDAO.delete(tsscGame);
		
		assertNull(gameDAO.findById(1));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameById() {
		TsscGame tsscGame = gameDAO.findById(1);
		
		assertNotNull(tsscGame);
		assertEquals("Sudoku", tsscGame.getName());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllGames() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setAdminPassword("5678");
		tsscGame.setGuestPassword("5678");
		tsscGame.setName("SushiGoParty");
		tsscGame.setScheduledDate(LocalDate.now());
		tsscGame.setScheduledTime(LocalTime.now());
		tsscGame.setUserPassword("5678");
		gameDAO.save(tsscGame);
		
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findAll();
		
		assertEquals(2, games.size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGamesByName() {
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setAdminPassword("5678");
		tsscGame2.setGuestPassword("5678");
		tsscGame2.setName("Sudoku");
		tsscGame2.setScheduledDate(LocalDate.now());
		tsscGame2.setScheduledTime(LocalTime.now());
		tsscGame2.setUserPassword("5678");
		gameDAO.save(tsscGame2);
		
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findByName("Sudoku");
		
		assertNotNull(games);
		assertEquals(2, games.size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByTopicDescription() {
		TsscTopic tsscTopic =  new TsscTopic();
		String description = "topic1";
		tsscTopic.setDescription(description);
		tsscTopic.setName("name1");
		tsscTopic.setDefaultSprints(2);
		tsscTopic.setDefaultGroups(2);
		tsscTopic.setGroupPrefix("pre1");
		
		TsscGame tsscGame1 = gameDAO.findById(1);
		tsscGame1.setTsscTopic(tsscTopic);
		
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setAdminPassword("5678");
		tsscGame2.setGuestPassword("5678");
		tsscGame2.setName("SushiGoParty");
		tsscGame2.setScheduledDate(LocalDate.now());
		tsscGame2.setScheduledTime(LocalTime.now());
		tsscGame2.setUserPassword("5678");
		gameDAO.save(tsscGame2);
		tsscGame2.setTsscTopic(tsscTopic);
		
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findByTopicDescription(description);
		
		assertNotNull(games);
		assertEquals(2, games.size());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByTopicId() {
		TsscTopic tsscTopic =  new TsscTopic();
		tsscTopic.setDescription("topic1");
		tsscTopic.setName("name1");
		tsscTopic.setDefaultSprints(2);
		tsscTopic.setDefaultGroups(2);
		tsscTopic.setGroupPrefix("pre1");
				
		TsscGame tsscGame1 = gameDAO.findById(1);
		tsscGame1.setTsscTopic(tsscTopic);
		
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setAdminPassword("5678");
		tsscGame2.setGuestPassword("5678");
		tsscGame2.setName("SushiGoParty");
		tsscGame2.setScheduledDate(LocalDate.now());
		tsscGame2.setScheduledTime(LocalTime.now());
		tsscGame2.setUserPassword("5678");
		gameDAO.save(tsscGame2);
		tsscGame2.setTsscTopic(tsscTopic);
		
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findByTopicId(tsscTopic.getId());
		
		assertNotNull(games);
		assertEquals(2, games.size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByDateRange() {
		//LocalDate localDate = 
		//gameDAO.findByDateRange(scheduledDate, scheduledDate2);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindGameByDateAndTimeRange() {
		
		//gameDAO.findByDateAndTimeRange(scheduledDate, localTime, localTime2);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicsByGameDate() {
		//first
		TsscGame tsscGame = new TsscGame();
		tsscGame.setAdminPassword("1234");
		tsscGame.setGuestPassword("1234");
		tsscGame.setName("Sudoku");
		tsscGame.setScheduledDate(LocalDate.now().minusYears(1l));
		tsscGame.setScheduledTime(LocalTime.now().minusHours(2l));
		tsscGame.setUserPassword("1234");

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicTest");
		topic.setDescription("TopicDescriptionTest");
		topic.setGroupPrefix("123");
		topicDAO.save(topic);

		tsscGame.setTsscTopic(topic);
		gameDAO.save(tsscGame);

		//dos
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setAdminPassword("12345");
		tsscGame2.setGuestPassword("12345");
		tsscGame2.setName("Sudoku2");
		tsscGame2.setScheduledDate(LocalDate.now().minusYears(1l));
		tsscGame2.setScheduledTime(LocalTime.now().minusHours(1l));
		tsscGame2.setUserPassword("12345");

		TsscTopic topic2 = new TsscTopic();
		topic2.setName("TopicTest2");
		topic2.setDescription("TopicDescriptionTest2");
		topic2.setGroupPrefix("1234");
		topicDAO.save(topic2);

		tsscGame2.setTsscTopic(topic2);
		gameDAO.save(tsscGame2);

		//tres
		TsscGame tsscGame3 = new TsscGame();
		tsscGame3.setAdminPassword("333");
		tsscGame3.setGuestPassword("333");
		tsscGame3.setName("Sudoku3");
		tsscGame3.setScheduledDate(LocalDate.now().minusYears(5l)); //no lo debe seleccionar
		tsscGame3.setScheduledTime(LocalTime.now().minusHours(1l));
		tsscGame3.setUserPassword("333");

		tsscGame3.setTsscTopic(topic2);
		gameDAO.save(tsscGame3);

		List<Object[]> objetos = gameDAO.findByDateAndReturnTopics(LocalDate.now().minusYears(1l));

		assertEquals("El primer topic debe ser topic test", "TopicTest", ((TsscTopic)objetos.get(0)[0]).getName());
		assertEquals("El primer juego del primer topic debe ser sudoku", "Sudoku", ((TsscGame)objetos.get(0)[1]).getName());

		assertEquals("El primer topic debe ser TopicTest2", "TopicTest2", ((TsscTopic)objetos.get(1)[0]).getName());
		assertEquals("El primer juego del primer topic debe ser Sudoku2", "Sudoku2", ((TsscGame)objetos.get(1)[1]).getName());	}
	
}