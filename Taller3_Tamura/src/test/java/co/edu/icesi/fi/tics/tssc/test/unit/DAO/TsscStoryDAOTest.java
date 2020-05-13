package co.edu.icesi.fi.tics.tssc.test.unit.DAO;

import java.math.BigDecimal;
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

import co.edu.icesi.fi.tics.tssc.dao.TsscStoryDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscStoryDAOTest extends TestCase {

	@Autowired
	private TsscStoryDAO storyDAO;

	@BeforeEach
	public void setUp() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setAltDescShown("altDescShown");
		tsscStory.setAltDescripton("altDescripton");
		tsscStory.setBusinessValue(new BigDecimal(17));
		tsscStory.setInitialSprint(new BigDecimal(17));
		tsscStory.setPriority(new BigDecimal(23));
		tsscStory.setShortDescription("shortDescription");
		
		storyDAO.save(tsscStory);		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveStory() {
		TsscStory tsscStory2 = new TsscStory();
		tsscStory2.setAltDescShown("altDescShown2");
		tsscStory2.setAltDescripton("altDescripton2");
		tsscStory2.setBusinessValue(new BigDecimal(17));
		tsscStory2.setInitialSprint(new BigDecimal(17));
		tsscStory2.setPriority(new BigDecimal(23));
		tsscStory2.setShortDescription("shortDescription2");
		
		storyDAO.save(tsscStory2);	
		
		assertNotNull(storyDAO.findById(3));
		assertEquals("shortDescription2", storyDAO.findById(3).getShortDescription());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateStory() {
		TsscStory tsscStory1 = storyDAO.findById(1);
		String newDescription = "shortDescription 2.0";
		tsscStory1.setShortDescription(newDescription);
		
		storyDAO.update(tsscStory1);
		
		assertEquals(newDescription, storyDAO.findById(1).getShortDescription());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteStory() {
		setUp();
		TsscStory tsscStory1 = storyDAO.findById(1);
		
		storyDAO.delete(tsscStory1);
		
		assertNull(storyDAO.findById(1));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindStoryById() {
		TsscStory tsscStory1 = storyDAO.findById(1);
		
		assertNotNull(tsscStory1);
		assertEquals("shortDescription", tsscStory1.getShortDescription());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllStories() {
		setUp();
		TsscStory tsscStory2 = new TsscStory();
		tsscStory2.setAltDescShown("altDescShown2");
		tsscStory2.setAltDescripton("altDescripton2");
		tsscStory2.setBusinessValue(new BigDecimal(17));
		tsscStory2.setInitialSprint(new BigDecimal(17));
		tsscStory2.setPriority(new BigDecimal(23));
		tsscStory2.setShortDescription("shortDescription2");
		storyDAO.save(tsscStory2);
		
		ArrayList<TsscStory> stories = (ArrayList<TsscStory>) storyDAO.findAll();

		assertEquals(2, stories.size());
	}
	
}