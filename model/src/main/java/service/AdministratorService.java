package service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.FlightDao;
import dao.ReservationDao;
import dao.TicketDao;
import entity.*;

@Named
public class AdministratorService {

	@Inject
	private FlightDao flightDao;
	@Inject
	private TicketDao ticketDao;
	@Inject
	private ReservationDao reservationDao;

	@Transactional
	public Flight createFlight(Flight f) {
		return flightDao.create(f);
	}

	@Transactional
	public Flight readFlight(Object id) {
		return flightDao.read(id);
	}

	@Transactional
	public Flight updateFlight(Flight f) {
		return flightDao.update(f);
	}

	@Transactional
	public void deleteFlight(Object id) {
		flightDao.delete(id);
	}

	@Transactional
	public void convertExpiredReservations() {
		List<Ticket> tickets = ticketDao.getTicketsForExpiredReservation();
		List<Reservation> reservations = reservationDao
				.findExpired();
		for (Ticket t : tickets) {
			ticketDao.delete(t.getId());
		}

		for (Reservation r : reservations) {
			reservationDao.delete(r.getId());
		}

	}

	@Transactional
	public List<Flight> findFlightsAll() {
		return flightDao.findAll();
	}
	
	@Transactional
	public List<Flight> findFlightsByNumber(String flightNumber) {
		List<Flight> flights = null;
		if (flightNumber != null) {
			flights = flightDao.findByFlightNumber(flightNumber);
		} else {
			flights = flightDao.findAll();
		}
		return flights;
	}
	
	@Transactional
	public long countExpiredReservations() {
		return reservationDao.countExpired();
	}
	

}
