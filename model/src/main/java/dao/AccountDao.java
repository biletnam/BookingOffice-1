package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Account;

public class AccountDao extends GenericDaoImpl<Account> {

	public AccountDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		entityManager = factory.createEntityManager();
	}
	
}
