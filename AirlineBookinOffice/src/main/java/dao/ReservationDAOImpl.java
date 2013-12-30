package dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Reservation;

public class ReservationDAOImpl extends DAOFactory implements ReservationDAO {
	private EntityManager entityManager;
	
	public ReservationDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public void create(Reservation t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Reservation t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Reservation t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Reservation read(int id) {
		Reservation t = null;
		entityManager.getTransaction().begin();
		t = entityManager.find(Reservation.class, id);
		entityManager.getTransaction().commit();
		
		return t;
	}

	@Override
	public List<Reservation> getExpiredReservations() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Timestamp now = new Timestamp(gc.getTime().getTime());
		
		TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r where r.dateReservation < ?1 and r.datePayment = null", Reservation.class);
		List<Reservation> listR = null;
		
		query.setParameter(1, now);
		listR = query.getResultList();	
		
		return listR;
	}

	@Override
	public List<Reservation> getActualReservations() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Timestamp now = new Timestamp(gc.getTime().getTime());
		
		TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r where r.dateReservation > ?1", Reservation.class);
		List<Reservation> listR = null;
		
		query.setParameter(1, now);
		listR = query.getResultList();	
		
		return listR;	
	}

}
