package service;

import java.util.Map;

import javax.inject.Named;


import org.springframework.transaction.annotation.Transactional;

//import dao.*;
import entity.*;

@Named
public class CustomerService {
//	private TicketDao ticketDao;
//	private ReservationDao reservationDao;
//	private FlightDao flightDao;
	
	@Transactional
	void addReservation(Map<Flight, Integer> tickets) {
//		reservationDao.create(r);
//		for (Ticket t : listT) {
//			t.setReservationId(r.getId());
//			t.setStatus(TicketStatus.BOOKED);
//			ticketDao.update(t);
//		}
	}
	
//	List<Flight> findNecesseryFlights(String arrival, Date departureDate) {
//		return flightDao.find(arrival, departureDate);
//	}
	
}
