package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entity.Reservation;

@Repository
public class ReservationDao extends GenericDaoImpl<Reservation> {

	public List<Reservation> findExpired() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Date now = gc.getTime();
		
		TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r where r.dateReservation < ?1 and r.paid = FALSE", Reservation.class);
		List<Reservation> reservations = null;
		
		query.setParameter(1, now);
		reservations = query.getResultList();	
		
		return reservations;
	}
	
	public long countExpired() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Date now = gc.getTime();
		
		TypedQuery<Long> query = entityManager.createQuery("SELECT count(r) FROM Reservation r where r.dateReservation < ?1 and r.paid = FALSE", Long.class);
		query.setParameter(1, now);
		long amountOfExpiredReservations = query.getSingleResult();	
		
		return amountOfExpiredReservations;
	}

	public List<Reservation> findActual() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Date now = gc.getTime();
		
		TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r where r.dateReservation > ?1 and r.paid = false", Reservation.class);
		List<Reservation> reservations = null;
		
		query.setParameter(1, now);
		reservations = query.getResultList();	
		
		return reservations;	
	}

}
