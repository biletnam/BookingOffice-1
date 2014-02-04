package service;

import java.util.List;

import model.Account;
import model.AccountRights;
import dao.AccountDAO1;
import dao.AccountRightsDAO1;


public class SecurityOfficerService {
	private AccountDAO1 accountDao;
	private AccountRightsDAO1 accountRightsDao;
	
	public SecurityOfficerService(AccountDAO1 accountDao,
			AccountRightsDAO1 accountRightsDao) {
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

