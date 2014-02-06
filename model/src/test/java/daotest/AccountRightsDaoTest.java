package daotest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Account;
import model.AccountRights;
import model.AccountRole;

import org.junit.*;

import dao.AccountDao;
import dao.AccountRightsDao;

public class AccountRightsDaoTest extends TestBase {
	private static AccountRightsDao accountRightsDao;
	private static AccountDao accountDao;

	@BeforeClass
	public static void getDAO() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		accountRightsDao = new AccountRightsDao();
		EntityManager entityManager1 = factory.createEntityManager();
		accountRightsDao.setEntityManager(entityManager1);
		accountDao = new AccountDao();
		EntityManager entityManager2 = factory.createEntityManager();
		accountDao.setEntityManager(entityManager2);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		accountRightsDao.getEntityManager().close();
		accountDao.getEntityManager().close();
	}

	@Test
	public void testCreate() {
		AccountRights ar = new AccountRights();
		AccountRole accountRole = AccountRole.ACCOUNTANT;
		boolean accountRoleActive = true;
		ar.setAccountRole(accountRole);
		ar.setAccountRoleActive(accountRoleActive);

		accountRightsDao.create(ar);
		int id = ar.getId();
		AccountRights arReaded = accountRightsDao.read(id);
		assertTrue(id == arReaded.getId());
		assertTrue(accountRole.equals(arReaded.getAccountRole()));
		assertTrue(accountRoleActive == ar.isAccountRoleActive());

		accountRightsDao.delete(id);

	}

	@Test
	public void testUpdate() {
		AccountRights ar = accountRightsDao.read(1);
		AccountRole accountRole = AccountRole.ANALITYC;
		ar.setAccountRole(accountRole);

		accountRightsDao.update(ar);

		AccountRights arReaded = accountRightsDao.read(1);

		assertTrue(ar.getId() == arReaded.getId());
		assertTrue(accountRole.equals(arReaded.getAccountRole()));

		accountRole = AccountRole.ADMINISTRATOR;
		ar.setAccountRole(accountRole);

		accountRightsDao.update(ar);
	}

	@Test
	public void testDelete() {
		AccountRights ar = new AccountRights();

		AccountRole accountRole = AccountRole.ACCOUNTANT;
		boolean accountRoleActive = true;
		ar.setAccountRole(accountRole);
		ar.setAccountRoleActive(accountRoleActive);

		accountRightsDao.create(ar);

		int id = ar.getId();

		AccountRights arReaded = accountRightsDao.read(id);
		accountRightsDao.delete(id);

		arReaded = accountRightsDao.read(id);
		assertTrue(arReaded == null);
	}

	@Test
	public void testRead() {
		AccountRights ar = accountRightsDao.read(2);

		assertTrue(ar.getId() == 2);
		assertTrue(ar.getAccountRole().equals(AccountRole.ACCOUNTANT));
		assertTrue(ar.isAccountRoleActive() == true);

	}

	@Test
	public void testGetAccountRights() {
		Account a = accountDao.read(2);
		List<AccountRights> accountRights = accountRightsDao
				.getAccountRights(a);
		assertTrue(accountRights.size() == 3);
		assertTrue(accountRights.get(0).getAccountRole()
				.equals(AccountRole.ADMINISTRATOR));
		assertTrue(accountRights.get(0).isAccountRoleActive() == false);

	}

}
