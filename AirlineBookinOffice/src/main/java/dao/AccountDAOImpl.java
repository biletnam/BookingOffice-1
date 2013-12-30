package dao;

import javax.persistence.EntityManager;

import model.Account;

public class AccountDAOImpl extends DAOFactory implements AccountDAO {
	private EntityManager entityManager;
	
	public AccountDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void create(Account t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Account t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Account t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Account read(int id) {
		Account t = null;
		entityManager.getTransaction().begin();
		t = entityManager.find(Account.class, id);
		entityManager.getTransaction().commit();
		
		return t;
	}

	
	

}
