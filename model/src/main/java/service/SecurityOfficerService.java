package service;

import javax.inject.Inject;
import javax.inject.Named;

import dao.AccountDao;
import entity.Account;

@Named
public class SecurityOfficerService {
	
	@Inject
	private AccountDao accountDao;

	void addAccount(Account a) {
		accountDao.create(a);
	}

	void editAccount(Account a) {
		accountDao.update(a);
	}

}
