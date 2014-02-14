package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entity.Account;

@Repository
public class AccountDao extends GenericDaoImpl<Account> {
	public List<Account> findAll() {
		TypedQuery<Account> query = entityManager
				.createQuery(
						"SELECT a FROM Account a",
						Account.class);
		List<Account> accounts =  query.getResultList();
		return accounts;
	}
}
