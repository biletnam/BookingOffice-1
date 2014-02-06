package daotest;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.*;

import dao.AccountDao;
import entity.Account;

public class AccountDaoTest extends TestBase {
	private static AccountDao accountDao;
	
	@BeforeClass
	public static void getDAO() throws Exception {
		accountDao = new AccountDao();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		EntityManager entityManager = factory.createEntityManager();
		accountDao.setEntityManager(entityManager);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		accountDao.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		Account a = new Account();
		
		String login = "Login4";
		String psw = "Psw4";
		String surname = "Surname4";
		String name = "Name4";
		String middlename = "Middlename4";
		boolean active = true;
		
		a.setLogin(login);
		a.setPsw(psw);
		a.setSurname(surname);
		a.setName(name);
		a.setMiddlename(middlename);
		a.setActive(active);

		accountDao.create(a);
		
		int id = a.getId();
		Account aReaded = accountDao.read(id);
		
		assertTrue(id == aReaded.getId());
		assertTrue(login.equals(aReaded.getLogin()));
		assertTrue(psw.equals(aReaded.getPsw()));
		assertTrue(surname.equals(aReaded.getSurname()));
		assertTrue(name.equals(aReaded.getName()));
		assertTrue(middlename.equals(aReaded.getMiddlename()));
		assertTrue(active == aReaded.isActive());
		
		accountDao.delete(id);
			
	}

	@Test
	public void testUpdate() {
		Account a = accountDao.read(1);
		boolean active = false;
		a.setActive(active);
		
		accountDao.update(a);
		
		Account aReaded = accountDao.read(1);
		
		assertTrue(a.getId() == aReaded.getId());
		assertTrue(a.getLogin().equals(aReaded.getLogin()));
		assertTrue(a.getPsw().equals(aReaded.getPsw()));
		assertTrue(a.getSurname().equals(aReaded.getSurname()));
		assertTrue(a.getName().equals(aReaded.getName()));
		assertTrue(a.getMiddlename().equals(aReaded.getMiddlename()));
		assertTrue(active == aReaded.isActive());
		
		active = true;
		a.setActive(active);;
		
		accountDao.update(a);
		
	}

	@Test
	public void testDelete() {
		Account a = new Account();
		
		String login = "Login4";
		String psw = "Psw4";
		String surname = "Surname4";
		String name = "Name4";
		String middlename = "Middlename4";
		boolean active = true;
		
		a.setLogin(login);
		a.setPsw(psw);
		a.setSurname(surname);
		a.setName(name);
		a.setMiddlename(middlename);
		a.setActive(active);
		
		accountDao.create(a);

		int id = a.getId();
		
		Account aReaded = accountDao.read(id);
		accountDao.delete(id);
		
		aReaded = accountDao.read(id);
		assertTrue(aReaded == null);
		
	}

	@Test
	public void testRead() {
		Account a = accountDao.read(2);
		
		assertTrue(a.getId() == 2);
		assertTrue(a.getLogin().equals("Login2"));
		assertTrue(a.getPsw().equals("Psw2"));
		assertTrue(a.getSurname().equals("Surname2"));
		assertTrue(a.getName().equals("Name2"));
		assertTrue(a.getMiddlename().equals("Middlename2"));
		assertTrue(a.isActive() == true);
		
	}

}
