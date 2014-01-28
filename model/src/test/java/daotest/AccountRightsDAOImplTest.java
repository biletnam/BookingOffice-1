package daotest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Account;
import model.AccountRights;
import model.AccountRole;

import org.junit.*;

import dao.AccountDAOImpl;
import dao.AccountRightsDAOImpl;
import dao.DAOFactory;

public class AccountRightsDAOImplTest extends IntegrationTestBase {
	private static AccountRightsDAOImpl accountRightsDAOImpl;
	private static AccountDAOImpl accountDAOImpl;
	
	
	@BeforeClass
	public static void getDAO() throws Exception {
		DAOFactory factory = new DAOFactory();
		accountRightsDAOImpl = factory.getAccountRightsDAOImpl();
		accountDAOImpl = factory.getAccountDAOImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		accountRightsDAOImpl.getEntityManager().close();
		accountDAOImpl.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		AccountRights ar = new AccountRights();
		
		//int accountId = 1;
		AccountRole accountRole = AccountRole.ACCOUNTANT;
		
		//ar.setAccountId(accountId);
		ar.setAccountRole(accountRole);
		
		accountRightsDAOImpl.create(ar);
		int id = ar.getId();
		AccountRights arReaded = accountRightsDAOImpl.read(id);
		assertTrue(id == arReaded.getId());
		//assertTrue(accountId == arReaded.getAccountId());
		assertTrue(accountRole.equals(arReaded.getAccountRole()));
		
		accountRightsDAOImpl.delete(ar);
		
	}

	@Test
	public void testUpdate() {
		AccountRights ar = accountRightsDAOImpl.read(1);
		AccountRole accountRole = AccountRole.OFFICER;
		ar.setAccountRole(accountRole);
		
		accountRightsDAOImpl.update(ar);
		
		AccountRights arReaded = accountRightsDAOImpl.read(1);
		
		assertTrue(ar.getId() == arReaded.getId());
		//assertTrue(ar.getAccountId() == arReaded.getAccountId());
		assertTrue(accountRole.equals(arReaded.getAccountRole()));
		
		accountRole = AccountRole.ADMINISTRATOR;
		ar.setAccountRole(accountRole);
		
		accountRightsDAOImpl.update(ar);
	}

	@Test
	public void testDelete() {
		AccountRights ar = new AccountRights();
		
		//int accountId = 2;
		AccountRole accountRole = AccountRole.ACCOUNTANT;
		
		//ar.setAccountId(accountId);
		ar.setAccountRole(accountRole);
		
		accountRightsDAOImpl.create(ar);

		int id = ar.getId();
		
		AccountRights arReaded = accountRightsDAOImpl.read(id);
		accountRightsDAOImpl.delete(arReaded);
		
		arReaded = accountRightsDAOImpl.read(id);
		assertTrue(arReaded == null);
	}

	@Test
	public void testRead() {
		AccountRights ar = accountRightsDAOImpl.read(2);
		
		assertTrue(ar.getId() == 2);
		//assertTrue(ar.getAccountId() == 1);
		assertTrue(ar.getAccountRole().equals(AccountRole.ACCOUNTANT));
		
	}

	@Test
	public void testGetAccountRights() {
		Account a = accountDAOImpl.read(3);
		List<AccountRights> listAr = accountRightsDAOImpl.getAccountRights(a);
		assertTrue(listAr.size() == 1);
		//assertTrue(listAr.get(0).getAccountId() == 3);
		assertTrue(listAr.get(0).getAccountRole().equals(AccountRole.OFFICER));
		
	}

}
