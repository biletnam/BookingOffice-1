package dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import model.DataForReport;
import model.Flight;
import model.Reservation;
import model.Ticket;
import model.TicketStatus;

public class TicketDAO extends GenericDAOImpl<Ticket> {
		
	public void updateTicketsStatus(List<Ticket> tickets, TicketStatus ticketStatus) {
		for (Ticket t : tickets) {
			t.setStatus(ticketStatus);
			update(t);
		}
	}

	public long getAmountOfTicketsForStatusForTheFlight(Flight flight, TicketStatus ticketStatus) {
		TypedQuery<Long> query = entityManager.createQuery("SELECT count(t.id) FROM Ticket t WHERE t.status = ?1 and t.flightId = ?2", Long.class);
		long amount = 0;
		query.setParameter(1, ticketStatus);
		query.setParameter(2, flight.getId());
		amount = query.getSingleResult();
		return amount;
	}

	public List<Ticket> getTicketsForStatusForTheFlight(Flight flight, TicketStatus ticketStatus) {
		
		TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket t WHERE t.status = ?1 and t.flightId = ?2", Ticket.class);
		List<Ticket> tickets = null;
		query.setParameter(1, ticketStatus);
		query.setParameter(2, flight.getId());
		tickets = query.getResultList();
		return tickets;
	}

	public List<Ticket> getTicketsForExpiredReservation() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.add(Calendar.DAY_OF_YEAR, -3);
		Date now = (Date) gc.getTime();
		
		TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket t, Reservation r where r.id = t.reservationId and r.dateReservation < ?1", Ticket.class);
		List<Ticket> tickets = null;
		query.setParameter(1, now);
		tickets = query.getResultList();	
		return tickets;
	}

	public List<Ticket> getTicketsForReservation(Reservation r) {
		int id = r.getId();
		TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket t WHERE t.reservationId = ?1", Ticket.class);
		List<Ticket> tickets = null;
		query.setParameter(1, id);
		tickets = query.getResultList();
		return tickets;
	}

	public List<DataForReport> selectDailyDataInTotal(Timestamp startDate, Timestamp endDate) {
		return null;
	}

	public List<DataForReport> selectDailyDataByArrivalPlace(Timestamp startDate, Timestamp endDate) {
		return null;
		
		//TODO
		/*TypedQuery<DataForReport> query = entityManager.createQuery("SELECT new entity.DataForReport(r.datePayment, f.arrival, count(t.id), f.ticketPrice, count(t.id) * f.ticketPrice) FROM Ticket t, Reservation r, Flight f "
				+ "WHERE f.id = t.reservationId and t.status = 2 and r.datePayment between ?1 and ?2 "
				+ "GROUP BY r.datePayment, f.arrival, f.ticketPrice", DataForReport.class);
		List<DataForReport> listD = null;
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		listD = query.getResultList();
		return listD;*/
		
	/*
	 * TypedQuery<DataForReport> query = entityManager.createQuery("SELECT new entity.DataForReport(r.datePayment, f.arrival, count(t.id), f.ticketPrice, count(t.id) * f.ticketPrice) FROM Ticket t, Reservation r, Flight f "
				+ "WHERE f.id = t.reservationId and t.status = 2 and r.datePayment between ?1 and ?2 "
				+ "GROUP BY r.datePayment, f.arrival, f.ticketPrice", DataForReport.class);
		List<DataForReport> listD = null;
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		listD = query.getResultList();
	 */
		
	/*
	* select DATE(RESERVATION.DATE_PAYMENT) as datePayment, FLIGHT.ARRIVAL, count(TICKET.ID), FLIGHT.TICKET_PRICE, count(TICKET.ID)*FLIGHT.TICKET_PRICE as sumTicket 
	from TICKET, RESERVATION, FLIGHT where 
	FLIGHT.ID = TICKET.FLIGHTID and
	RESERVATION.ID = TICKET.RESERVATIONID and
	TICKET.STATUS = 3 and 
	RESERVATION.DATE_PAYMENT >='2013-12-05 00:00:00' 
	and RESERVATION.DATE_PAYMENT < '2013-12-22 00:00:00'
	group by RESERVATION.DATE_PAYMENT, FLIGHT.ARRIVAL, FLIGHT.TICKET_PRICE
	 */
	}
}
