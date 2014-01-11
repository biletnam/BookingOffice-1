package dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import model.*;


public class FlightDAOImpl extends DAOFactory implements FlightDAO {
	private EntityManager entityManager;
	
	public FlightDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void create(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Flight read(int id) {
		Flight t = null;
		entityManager.getTransaction().begin();
		t = entityManager.find(Flight.class, id);
		entityManager.getTransaction().commit();
		return t;
	}

	@Override
	public List<Flight> find(String arrival, Date departureDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(departureDate);
		gc.add(Calendar.DAY_OF_YEAR, 1);
		Date departureDate2 = new Date(gc.getTime().getTime());
		TypedQuery<Flight> query = entityManager.createQuery("SELECT f FROM Flight f WHERE f.arrival = ?1 and (f.dateDeparture BETWEEN ?2 and ?3)", Flight.class);
		List<Flight> listF = null;
		query.setParameter(1, arrival);
		query.setParameter(2, departureDate);
		query.setParameter(3, departureDate2);
		listF = query.getResultList();
		return listF;
	}

}
