package service;

import java.util.List;

import dao.FlightDao;
import dao.ReservationDao;
import dao.TicketDao;
import entity.*;

public class AdministratorService {
	private FlightDao flightDao;
	private TicketDao ticketDao;
	private ReservationDao reservationDao;

	void addFlight(Flight f) {
		flightDao.create(f);
	}
	
	void editFlight(Flight f) {
		flightDao.update(f);
	}
	
	void deleteFlight(Flight f) {
		flightDao.delete(f.getId());;
	}
	
	void convertTickets() {
		List<Ticket> tickets = ticketDao.getTicketsForExpiredReservation();
		List<Reservation> reservations = reservationDao.getExpiredReservations();
		for (Ticket t : tickets) {
			ticketDao.delete(t.getId());
		}
		
		for (Reservation r : reservations) {
			reservationDao.delete(r.getId());
		}

	}

}
