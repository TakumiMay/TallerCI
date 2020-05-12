package co.edu.icesi.fi.tics.tssc.test.unit.DAO;

import java.util.ArrayList;

import org.junit.Test;
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

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTopic() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("name");
		
		topicDAO.save(tsscTopic);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		
		topicDAO.update(tsscTopic);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTopic() {
		
		topicDAO.delete(tsscTopic);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicById() {
		
		topicDAO.findById(id);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllTopics() {
		ArrayList<TsscTopic> topics = (ArrayList<TsscTopic>) topicDAO.findAll();
//		System.out.println();
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByName() {
	
		topicDAO.findByName(name);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByDescription() {
		
		topicDAO.findByDescription(description);
	}
	
}