package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Account;
import model.AccountRights;

public class AccountRightsDao extends GenericDaoImpl<AccountRights> {
	
	public List<AccountRights> getAccountRights(Account account) {
		TypedQuery<AccountRights> query = entityManager.createQuery(
				"SELECT ar FROM AccountRights ar WHERE ar.account.id = ?1",
				AccountRights.class);

		List<AccountRights> accountRights = null;
		query.setParameter(1, account.getId());
		accountRights = query.getResultList();

		return accountRights;

	}

}
