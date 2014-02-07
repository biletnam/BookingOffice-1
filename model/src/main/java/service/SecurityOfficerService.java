package service;

import dao.AccountDao;
import entity.Account;

public class SecurityOfficerService {
	private AccountDao accountDao;

	void addAccount(Account a) {
		accountDao.create(a);
	}

	void editAccount(Account a) {
		accountDao.update(a);
	}

}
