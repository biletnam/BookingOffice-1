package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import entity.*;

@Repository
public class FlightDao extends GenericDaoImpl<Flight> {

	public List<Flight> findByFlightNumber(String flightNumber) {
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.flightNumber = ?1",
						Flight.class);
		query.setParameter(1, flightNumber);
		List<Flight> flights = query.getResultList();
		return flights;
	}
	
	public List<Flight> findAll() {
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f",
						Flight.class);
		List<Flight> flights =  query.getResultList();
		return flights;
	}
	
	public List<Flight> findByDateDeparture(Date dateDeparture) {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dateDeparture);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		Date startDate = gc.getTime();
		gc.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = gc.getTime();
		
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.dateDeparture between ?1 and ?2",
						Flight.class);
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		List<Flight> flights = query.getResultList();
		return flights;
	}
	
	public List<Flight> findByArrival(String arrival) {
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.arrival = ?1",
						Flight.class);
		query.setParameter(1, arrival);
		List<Flight> flights = query.getResultList();
		return flights;
	}
	
	public List<Flight> findByDateDepartureAndArrival(Date dateDeparture, String arrival) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dateDeparture);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		Date startDate = gc.getTime();
		gc.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = gc.getTime();
		
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.arrival = ?1 and (f.dateDeparture BETWEEN ?2 and ?3)",
						Flight.class);
		query.setParameter(1, arrival);
		query.setParameter(2, startDate);
		query.setParameter(3, endDate);
		List<Flight> flights = query.getResultList();
		return flights;
	}

}
