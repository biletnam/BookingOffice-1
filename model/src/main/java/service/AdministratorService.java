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
	public void updateFlight(Flight f) {
		Flight flight = flightDao.read(f.getId());
		int ticketAmountNew = f.getTicketAmount();
		int ticketAmount = flight.getTicketAmount();
		int ticketFreeAmount = flight.getTicketFreeAmount();
		f.setTicketFreeAmount(ticketFreeAmount + ticketAmountNew - ticketAmount);
		flightDao.update(f);
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
		return flightDao.findActualAll();
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
	public List<Flight> findActualFlightsByDateDepartureAndArrival(
			Date dateDeparture, String arrival) {
		List<Flight> flights = null;
		if (dateDeparture == null && arrival.equals("")) {
			flights = flightDao.findActualAll();
		} else if (dateDeparture != null && arrival.equals("")) {
			flights = flightDao.findActualByDateDeparture(dateDeparture);
		} else if (dateDeparture == null && !arrival.equals("")) {
			flights = flightDao.findActualByArrival(arrival);
		} else {
			flights = flightDao.findActualByDateDepartureAndArrival(
					dateDeparture, arrival);
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

	@Transactional
	public long countBookedTickets(Flight flight) {
		return ticketDao.countBooked(flight);
	}

}
