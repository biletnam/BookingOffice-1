package dao;

import javax.persistence.*;

public class DAOFactory {
	private static final String UNIT_NAME = "BookingOfficeTest";
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	
	
	public EntityManager getEntityManager() {
		factory = Persistence.createEntityManagerFactory(UNIT_NAME);
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	public FlightDAOImpl getFlightDAOImpl() {
		return new FlightDAOImpl(getEntityManager());
	}

	public TicketDAOImpl getTicketDAOImpl() {
		return new TicketDAOImpl(getEntityManager());
	}

	public ReservationDAOImpl getReservationDAOImpl() {
		return new ReservationDAOImpl(getEntityManager());
	}

	public AccountDAOImpl getAccountDAOImpl() {
		return new AccountDAOImpl(getEntityManager());
	}
	
	public AccountRightsDAOImpl getAccountRightsDAOImpl() {
		return new AccountRightsDAOImpl(getEntityManager());
	}
	
}
