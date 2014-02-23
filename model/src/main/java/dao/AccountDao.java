package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entity.Account;

@Repository
public class AccountDao extends GenericDaoImpl<Account> {
	public Account check(String login, String psw) {
		TypedQuery<Account> query = entityManager.createQuery(
				"SELECT a FROM Account a where a.login = ?1 and a.psw = ?2 and a.active = true",
				Account.class);
		query.setParameter(1, login);
		query.setParameter(2, psw);
		Account account = query.getSingleResult();
		return account;
	}

	public List<Account> findAll() {
		TypedQuery<Account> query = entityManager.createQuery(
				"SELECT a FROM Account a", Account.class);
		List<Account> accounts = query.getResultList();
		return accounts;
	}
}
