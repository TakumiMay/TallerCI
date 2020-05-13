package co.edu.icesi.fi.tics.tssc.test.unit.DAO;

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

import co.edu.icesi.fi.tics.tssc.dao.TsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscTopicDAOTest extends TestCase {

	@Autowired
	private TsscTopicDAO topicDAO;

	@BeforeEach
	public void setUp() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setDescription("description1");
		tsscTopic.setName("name1");
		tsscTopic.setDefaultSprints(2);
		tsscTopic.setDefaultGroups(2);
		tsscTopic.setGroupPrefix("pre1");
		
		topicDAO.save(tsscTopic);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTopic() {
		TsscTopic tsscTopic2 = new TsscTopic();
		tsscTopic2.setDescription("description2");
		tsscTopic2.setName("name2");
		tsscTopic2.setDefaultSprints(2);
		tsscTopic2.setDefaultGroups(2);
		tsscTopic2.setGroupPrefix("pre2");
		
		topicDAO.save(tsscTopic2);
		
		assertNotNull(topicDAO.findById(2));
		assertNotNull(topicDAO.findByName("name2"));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		TsscTopic tsscTopic = topicDAO.findById(1);
		String newName = "name1 2.0";
		tsscTopic.setName(newName);
		
		topicDAO.update(tsscTopic);
		
		assertEquals(newName, topicDAO.findById(1).getName());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTopic() {
		TsscTopic tsscTopic = topicDAO.findById(1);
		
		topicDAO.delete(tsscTopic);
		
		assertNull(topicDAO.findById(1));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicById() {
		TsscTopic tsscTopic = topicDAO.findById(1);
		
		assertNotNull(tsscTopic);
		assertEquals("name1", tsscTopic.getName());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllTopics() {
		TsscTopic tsscTopic2 = new TsscTopic();
		tsscTopic2.setDescription("description2");
		tsscTopic2.setName("name2");
		tsscTopic2.setDefaultSprints(2);
		tsscTopic2.setDefaultGroups(2);
		tsscTopic2.setGroupPrefix("pre2");
		topicDAO.save(tsscTopic2);
		
		ArrayList<TsscTopic> topics = (ArrayList<TsscTopic>) topicDAO.findAll();

		assertEquals(2, topics.size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByName() {
		TsscTopic tsscTopic2 = new TsscTopic();
		tsscTopic2.setDescription("description2");
		tsscTopic2.setName("name1");
		tsscTopic2.setDefaultSprints(2);
		tsscTopic2.setDefaultGroups(2);
		tsscTopic2.setGroupPrefix("pre2");
		topicDAO.save(tsscTopic2);
		
		ArrayList<TsscTopic> topics = (ArrayList<TsscTopic>) topicDAO.findByName("name1");
		
		assertNotNull(topics);
		assertEquals(2, topics.size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByDescription() {
		TsscTopic tsscTopic2 = new TsscTopic();
		tsscTopic2.setDescription("description1");
		tsscTopic2.setName("name2");
		tsscTopic2.setDefaultSprints(2);
		tsscTopic2.setDefaultGroups(2);
		tsscTopic2.setGroupPrefix("pre2");
		topicDAO.save(tsscTopic2);
		
		ArrayList<TsscTopic> topics = (ArrayList<TsscTopic>) topicDAO.findByDescription("description1");
		
		assertNotNull(topics);
		assertEquals(2, topics.size());
	}
	
}