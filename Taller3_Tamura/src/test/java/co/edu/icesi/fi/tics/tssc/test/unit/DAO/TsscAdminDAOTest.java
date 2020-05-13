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

import co.edu.icesi.fi.tics.tssc.dao.TsscAdminDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TsscAdminDAOTest extends TestCase {

	@Autowired
	private TsscAdminDAO adminDAO;

	@BeforeEach
	public void setUp() {
		TsscAdmin superAdmin1 = new TsscAdmin();
		superAdmin1.setUser("Mayumi");
		superAdmin1.setPassword("{noop}1234");
		superAdmin1.setSuperAdmin("superAdministrator");
		adminDAO.save(superAdmin1);
		
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setUser("Leo");
		admin2.setPassword("{noop}5678");
		admin2.setSuperAdmin("administrator");
		adminDAO.save(admin2);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAdmin() {
		TsscAdmin tsscAdmin3 = new TsscAdmin();
		tsscAdmin3.setUser("user3");
		tsscAdmin3.setPassword("{noop}5678");
		tsscAdmin3.setSuperAdmin("administrator");
		
		adminDAO.save(tsscAdmin3);
		
		assertNotNull(adminDAO.findById(3));
		assertEquals("user3", adminDAO.findById(3).getUser());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateAdmin() {
		TsscAdmin tsscAdmin1 = adminDAO.findById(1);
		String newName = "Mayumi 2.0";
		tsscAdmin1.setUser(newName);
		
		adminDAO.update(tsscAdmin1);
		
		assertEquals(newName, adminDAO.findById(1).getUser());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteAdmin() {
		TsscAdmin tsscAdmin1 = adminDAO.findById(1);
		
		adminDAO.delete(tsscAdmin1);
		
		assertNull(adminDAO.findById(1));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAdminById() {
		TsscAdmin tsscAdmin1 = adminDAO.findById(1);
		
		assertNotNull(tsscAdmin1);
		assertEquals("Mayumi", tsscAdmin1.getUser());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAllAdmins() {
		TsscAdmin tsscAdmin3 = new TsscAdmin();
		tsscAdmin3.setUser("user3");
		tsscAdmin3.setPassword("{noop}5678");
		tsscAdmin3.setSuperAdmin("administrator");
		adminDAO.save(tsscAdmin3);
		
		ArrayList<TsscAdmin> admins = (ArrayList<TsscAdmin>) adminDAO.findAll();

		assertEquals(3, admins.size());
	}
	
}