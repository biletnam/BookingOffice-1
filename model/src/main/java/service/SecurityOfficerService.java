package service;

import java.util.List;

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
	public Account createAccount(Account a) {
		return accountDao.create(a);
	}

	@Transactional
	public Account updateAccount(Account a) {
		return accountDao.update(a);
	}
	
	@Transactional
	public Account readAccount(Object id) {
		return accountDao.read(id);
	}
	
	@Transactional
	public void deleteAccount(Object id) {
		accountDao.delete(id);
	}
	
	@Transactional
	public List<Account> findAllAccounts() {
		return accountDao.findAll();
	}
	
	@Transactional
	public Account checkUser(String login, String psw) {
		return accountDao.check(login, psw);
	}

}
