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
	public void addFlight(Flight f) {
		flightDao.create(f);
	}

	@Transactional
	public void editFlight(Flight f) {
		flightDao.update(f);
	}

	@Transactional
	public void deleteFlight(Flight f) {
		flightDao.delete(f.getId());
		;
	}

	@Transactional
	public void convertTickets() {
		List<Ticket> tickets = ticketDao.getTicketsForExpiredReservation();
		List<Reservation> reservations = reservationDao
				.getExpiredReservations();
		for (Ticket t : tickets) {
			ticketDao.delete(t.getId());
		}

		for (Reservation r : reservations) {
			reservationDao.delete(r.getId());
		}

	}

	@Transactional
	public List<Flight> findFlights(String flightNumber) {
		List<Flight> flights = null;
		if (flightNumber != null) {
			flights = flightDao.findByFlightNumber(flightNumber);
		} else {
			flights = flightDao.findAll();
		}
		return flights;
	}

}
