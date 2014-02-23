package service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.FlightDao;
import dao.ReservationDao;
import dao.TicketDao;
import entity.*;

@Named
public class CustomerService {
	@Inject
	private TicketDao ticketDao;
	@Inject
	private ReservationDao reservationDao;
	@Inject
	private FlightDao flightDao;

	@Transactional
	public void createReservation(List<Map.Entry<Flight, Integer>> tickets,
			String customerSurname, String customerName,
			String customerMiddlename, String customerEmail, double sumTotal) {
		Reservation reservation = new Reservation();
		reservation.setCustomerSurname(customerSurname);
		reservation.setCustomerName(customerName);
		reservation.setCustomerMiddlename(customerMiddlename);
		reservation.setCustomerEmail(customerEmail);
		reservation.setSumTotal(sumTotal);
		reservation = reservationDao.create(reservation);

		for (Map.Entry<Flight, Integer> entry : tickets) {
			Flight flight = entry.getKey();
			int quantity = entry.getValue();
			int freeTickets = flight.getTicketFreeAmount() - entry.getValue();
			flight.setTicketFreeAmount(freeTickets);
			for (int i = 1; i <= quantity; i++) {
				Ticket ticket = new Ticket();
				ticket.setFlightId(flight.getId());
				ticket.setReservationId(reservation.getId());
				ticket.setStatus(TicketStatus.BOOKED);
				ticketDao.create(ticket);
			}
		}

	}
	
	@Transactional
	public void cancelReservation(List<Map.Entry<Flight, Integer>> tickets) {
		for(Map.Entry<Flight, Integer> entry : tickets) {
			Flight flightEntry = entry.getKey();
			Flight flight = flightDao.read(flightEntry.getId());
			int ticketFreeAmount = flight.getTicketFreeAmount() + entry.getValue();
			flight.setTicketFreeAmount(ticketFreeAmount);
			flightDao.update(flight);
		}
	}
	
	@Transactional
	public void updateFlightCart(Flight f, int amount) {
		Flight flight = flightDao.read(f.getId());
		int ticketFreeAmount = flight.getTicketFreeAmount() - amount;
		flight.setTicketFreeAmount(ticketFreeAmount);
		flightDao.update(flight);
	}
}
