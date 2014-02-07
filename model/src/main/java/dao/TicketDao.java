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
	
	public List<DataForReport> selectDataByDay(Date startDate,
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

//		SELECT SOLDTICKETSBYDAY.TICKETDAYPAYMENT, count(SOLDTICKETSBYDAY.TICKETID), sum(SOLDTICKETSBYDAY.TICKETPRICE) from SOLDTICKETSBYDAY
//		GROUP BY SOLDTICKETSBYDAY.TICKETDAYPAYMENT;
		
//		TypedQuery<DataForReport> query = entityManager
//				.createQuery(
//						"SELECT new entity.DataForReport(t.datePayment, count(t.id), (count(t.id) * f.ticketPrice)) FROM Ticket t, Flight f "
//								+ "WHERE f.id = t.flightId and t.status = ?3 and t.datePayment between ?1 and ?2 "
//								+ "GROUP BY t.datePayment, f.ticketPrice",
//						DataForReport.class);
//		List<DataForReport> data = null;
//		query.setParameter(1, startDate);
//		query.setParameter(2, endDate);
//		query.setParameter(3, TicketStatus.SOLD);
//		data = query.getResultList();
//		return data;
	}

	public List<DataForReport> selectDataByArrivalPlace(
			Date startDate, Date endDate) {

		return null;
//		TypedQuery<DataForReport> query = entityManager
//				.createQuery(
//						"SELECT new entity.DataForReport(f.arrival, count(t.id), f.ticketPrice, (count(t.id) * f.ticketPrice)) FROM Ticket t, Flight f "
//								+ "WHERE f.id = t.flightId and t.status = ?3 and t.datePayment between ?1 and ?2 "
//								+ "GROUP BY f.arrival, f.ticketPrice",
//						DataForReport.class);
//		List<DataForReport> data = null;
//		query.setParameter(1, startDate);
//		query.setParameter(2, endDate);
//		query.setParameter(3, TicketStatus.SOLD);
//		data = query.getResultList();
//		return data;

	}
}
