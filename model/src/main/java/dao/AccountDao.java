package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Account;

public class AccountDao extends GenericDaoImpl<Account> {

	public AccountDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		entityManager = factory.createEntityManager();
	}
	
}
