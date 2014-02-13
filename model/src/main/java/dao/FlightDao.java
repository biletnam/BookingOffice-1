package dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import entity.*;

@Repository
public class FlightDao extends GenericDaoImpl<Flight> {

	public List<Flight> findByArrivalAndDepartureDate(String arrival, Date departureDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(departureDate);
		gc.add(Calendar.DAY_OF_YEAR, 1);
		Date departureDate2 = new Date(gc.getTime().getTime());
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.arrival = ?1 and (f.dateDeparture BETWEEN ?2 and ?3)",
						Flight.class);
		query.setParameter(1, arrival);
		query.setParameter(2, departureDate);
		query.setParameter(3, departureDate2);
		List<Flight> flights = query.getResultList();
		return flights;
	}
	
	public List<Flight> findByFlightNumber(String flightNumber) {
		TypedQuery<Flight> query = entityManager
				.createQuery(
						"SELECT f FROM Flight f WHERE f.flightNumber like ?1",
						Flight.class);
		query.setParameter(1, "%" + flightNumber + "%");
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

}
