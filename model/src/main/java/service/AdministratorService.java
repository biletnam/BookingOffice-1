package service;

import java.util.Date;
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
	public boolean updateFlight(Flight f) {
		Flight flight = flightDao.read(f.getId());
		int newTicketAmount = f.getTicketAmount();
		int ticketFreeAmount = flight.getTicketFreeAmount();
		int ticketAmount = flight.getTicketAmount();
		if (newTicketAmount >= ticketAmount - ticketFreeAmount) {
			flightDao.update(f);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void deleteFlight(Object id) {
		flightDao.delete(id);
	}

	@Transactional
	public void convertExpiredReservations() {
		List<Reservation> reservations = reservationDao.findExpired();
		for (Reservation r : reservations) {
			List<Ticket> tickets = ticketDao.getTicketsForReservation(r);
			for (Ticket t : tickets) {
				int flightId = t.getFlightId();
				Flight flight = flightDao.read(flightId);
				int ticketFreeAmount = flight.getTicketFreeAmount();
				ticketFreeAmount = ticketFreeAmount + 1;
				flight.setTicketFreeAmount(ticketFreeAmount);
				ticketDao.delete(t.getId());
			}
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
		if (flightNumber.equals("")) {
			flights = flightDao.findAll();
		} else {
			flights = flightDao.findByFlightNumber(flightNumber);
		}
		return flights;
	}

	@Transactional
	public List<Flight> findFlightsByDateDepartureAndArrival(
			Date dateDeparture, String arrival) {
		List<Flight> flights = null;
		if (dateDeparture == null && arrival.equals("")) {
			flights = flightDao.findAll();
		} else if (dateDeparture != null && arrival.equals("")) {
			flights = flightDao.findByDateDeparture(dateDeparture);
		} else if (dateDeparture == null && !arrival.equals("")) {
			flights = flightDao.findByArrival(arrival);
		} else {
			flights = flightDao.findByDateDepartureAndArrival(dateDeparture,
					arrival);
		}

		return flights;
	}

	@Transactional
	public long countExpiredReservations() {
		return reservationDao.countExpired();
	}
	
	@Transactional
	public long countSoldTickets(Flight flight) {
		return ticketDao.countSold(flight);
	}

}
