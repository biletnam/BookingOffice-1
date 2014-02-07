package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import entity.DataForReport;
import entity.Flight;
import entity.Reservation;
import entity.Ticket;
import entity.TicketStatus;

public class TicketDao extends GenericDaoImpl<Ticket> {

	public void updateTicketsStatus(List<Ticket> tickets,
			TicketStatus ticketStatus) {
		for (Ticket t : tickets) {
			t.setStatus(ticketStatus);
			update(t);
		}
	}

	public long getAmountOfTicketsForStatusForTheFlight(Flight flight,
			TicketStatus ticketStatus) {
		TypedQuery<Long> query = entityManager
				.createQuery(
						"SELECT count(t.id) FROM Ticket t WHERE t.status = ?1 and t.flightId = ?2",
						Long.class);
		long amount = 0;
		query.setParameter(1, ticketStatus);
		query.setParameter(2, flight.getId());
		amount = query.getSingleResult();
		return amount;
	}

	public List<Ticket> getTicketsForStatusForTheFlight(Flight flight,
			TicketStatus ticketStatus) {

		TypedQuery<Ticket> query = entityManager
				.createQuery(
						"SELECT t FROM Ticket t WHERE t.status = ?1 and t.flightId = ?2",
						Ticket.class);
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

		TypedQuery<Ticket> query = entityManager
				.createQuery(
						"SELECT t FROM Ticket t, Reservation r where r.id = t.reservationId and r.dateReservation < ?1",
						Ticket.class);
		List<Ticket> tickets = null;
		query.setParameter(1, now);
		tickets = query.getResultList();
		return tickets;
	}

	public List<Ticket> getTicketsForReservation(Reservation r) {
		int id = r.getId();
		TypedQuery<Ticket> query = entityManager.createQuery(
				"SELECT t FROM Ticket t WHERE t.reservationId = ?1",
				Ticket.class);
		List<Ticket> tickets = null;
		query.setParameter(1, id);
		tickets = query.getResultList();
		return tickets;
	}
	
	public List<DataForReport> getDataByDay(Date startDate,
			Date endDate) {
		TypedQuery<DataForReport> query = entityManager.createQuery("SELECT new entity.DataForReport(st.ticketDatePayment, count(st.ticketId), sum(st.ticketPrice)) "
				+ "FROM SoldTickets st "
				+ "WHERE st.ticketDatePayment BETWEEN ?1 and ?2 "
				+ "GROUP BY st.ticketDatePayment", DataForReport.class);
		List<DataForReport> data = null;
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		data = query.getResultList();
		
		return data;
	}

	public List<DataForReport> getDataByArrival(
			Date startDate, Date endDate) {

		TypedQuery<DataForReport> query = entityManager.createQuery("SELECT new entity.DataForReport(st.ticketArrival, count(st.ticketId), sum(st.ticketPrice)) "
				+ "FROM SoldTickets st "
				+ "WHERE st.ticketDatePayment BETWEEN ?1 and ?2 "
				+ "GROUP BY st.ticketArrival", DataForReport.class);
		List<DataForReport> data = null;
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		data = query.getResultList();
		
		return data;

	}
}
