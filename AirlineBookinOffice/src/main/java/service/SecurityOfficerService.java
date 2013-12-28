package service;

import java.util.List;

import dao.AccountDAO;
import dao.AccountRightsDAO;
import entity.Account;
import entity.AccountRights;


public class SecurityOfficerService {
	private AccountDAO accountDao;
	private AccountRightsDAO accountRightsDao;
	
	public SecurityOfficerService(AccountDAO accountDao,
			AccountRightsDAO accountRightsDao) {
		super();
		this.accountDao = accountDao;
		this.accountRightsDao = accountRightsDao;
	}
	
	void createAccount(Account a, List<AccountRights> listAr) {
		accountDao.create(a);
		for (AccountRights ar : listAr) {
			accountRightsDao.create(ar);
		}
		
	}
	
	void editAccount(Account a) {
		accountDao.update(a);
	}
	
	void editAccountsRights(Account a, List<AccountRights> listArNew) {
		List<AccountRights> listAr = accountRightsDao.getAccountRights(a);
		for (AccountRights ar : listAr) {
			accountRightsDao.delete(ar);
		}
		
		for (AccountRights ar : listArNew) {
			accountRightsDao.create(ar);
		}
	}
	
	void deactivateAccount(Account a) {
		a.setActive(false);
		accountDao.update(a);
	}
}

