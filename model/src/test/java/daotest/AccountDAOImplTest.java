package daotest;

import static org.junit.Assert.assertTrue;
import model.Account;

import org.junit.*;

import dao.AccountDAOImpl;
import dao.DAOFactory;

public class AccountDAOImplTest extends TestBase {
	private static AccountDAOImpl accountDAOImpl;
	
	@BeforeClass
	public static void getDAO() throws Exception {
		DAOFactory factory = new DAOFactory();
		accountDAOImpl = factory.getAccountDAOImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		accountDAOImpl.getEntityManager().close();
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

		accountDAOImpl.create(a);
		int id = a.getId();
		Account aReaded = accountDAOImpl.read(id);
		assertTrue(id == aReaded.getId());
		assertTrue(login.equals(aReaded.getLogin()));
		assertTrue(psw.equals(aReaded.getPsw()));
		assertTrue(surname.equals(aReaded.getSurname()));
		assertTrue(name.equals(aReaded.getName()));
		assertTrue(middlename.equals(aReaded.getMiddlename()));
		assertTrue(active == aReaded.isActive());
		
		accountDAOImpl.delete(a);
			
	}

	@Test
	public void testUpdate() {
		Account a = accountDAOImpl.read(1);
		boolean active = false;
		a.setActive(active);
		
		accountDAOImpl.update(a);
		
		Account aReaded = accountDAOImpl.read(1);
		
		assertTrue(a.getId() == aReaded.getId());
		assertTrue(a.getLogin().equals(aReaded.getLogin()));
		assertTrue(a.getPsw().equals(aReaded.getPsw()));
		assertTrue(a.getSurname().equals(aReaded.getSurname()));
		assertTrue(a.getName().equals(aReaded.getName()));
		assertTrue(a.getMiddlename().equals(aReaded.getMiddlename()));
		assertTrue(active == aReaded.isActive());
		
		active = true;
		a.setActive(active);;
		
		accountDAOImpl.update(a);
		
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
		
		accountDAOImpl.create(a);

		int id = a.getId();
		
		Account aReaded = accountDAOImpl.read(id);
		accountDAOImpl.delete(aReaded);
		
		aReaded = accountDAOImpl.read(id);
		assertTrue(aReaded == null);
		
	}

	@Test
	public void testRead() {
		Account a = accountDAOImpl.read(2);
		
		assertTrue(a.getId() == 2);
		assertTrue(a.getLogin().equals("Login2"));
		assertTrue(a.getPsw().equals("Psw2"));
		assertTrue(a.getSurname().equals("Surname2"));
		assertTrue(a.getName().equals("Name2"));
		assertTrue(a.getMiddlename().equals("Middlename2"));
		assertTrue(a.isActive() == true);
		
	}

}
