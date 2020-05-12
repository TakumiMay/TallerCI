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

import co.edu.icesi.fi.tics.tssc.dao.TsscGameDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscGameDAOTest extends TestCase {

	@Autowired
	TsscGameDAO gameDAO;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crudPersona() {
		TsscGame game = new TsscGame();
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setName("hola");
		game.setUserPassword("1234");
		gameDAO.save(game);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAll() {
		ArrayList<TsscGame> games = (ArrayList<TsscGame>) gameDAO.findAll();
//		System.out.println();
	}

}