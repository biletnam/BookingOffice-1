package service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.AccountDao;
import entity.Account;

@Named
public class SecurityOfficerService {
	
	@Inject
	private AccountDao accountDao;

	@Transactional
	public void addAccount(Account a) {
		accountDao.create(a);
	}

	@Transactional
	public void editAccount(Account a) {
		accountDao.update(a);
	}

}
