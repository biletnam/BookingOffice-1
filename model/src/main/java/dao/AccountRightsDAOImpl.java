package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Account;
import model.AccountRights;


public class AccountRightsDAOImpl extends DAOFactory implements AccountRightsDAO {
	private EntityManager entityManager;
	
	public AccountRightsDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void create(AccountRights t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(AccountRights t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(AccountRights t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public AccountRights read(int id) {
		AccountRights t = null;
		entityManager.getTransaction().begin();
		t = entityManager.find(AccountRights.class, id);
		entityManager.getTransaction().commit();
		
		return t;
	}

	@Override
	public List<AccountRights> getAccountRights(Account account) {
		TypedQuery<AccountRights> query = entityManager.createQuery("SELECT ar FROM AccountRights ar WHERE ar.accountId = ?1", AccountRights.class);
		
		List<AccountRights> listAr = null;
		query.setParameter(1, account.getId());		
		listAr = query.getResultList();	
		
		return listAr;
		
	}


}
