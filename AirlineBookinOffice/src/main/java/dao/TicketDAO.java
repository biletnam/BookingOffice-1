package dao;

import java.sql.Timestamp;
import java.util.*;

import model.*;

public interface TicketDAO extends DAO<Ticket> {
	long getAmountOfTicketsForStatus(Flight flight, TicketStatus ticketStatus);
	List<Ticket> getTicketsForStatus(Flight flight, TicketStatus ticketStatus);
	List<Ticket> getTicketsForExpiredReservation();
	List<Ticket> getTicketsForReservation(Reservation r);
	void updateTicketsStatus(List<Ticket> tickets, TicketStatus ticketStatus);
	List<DataForReport> selectDailyDataInTotal(Timestamp startDate, Timestamp endDate);
	List<DataForReport> selectDailyDataByArrivalPlace(Timestamp startDate, Timestamp endDate);
}
