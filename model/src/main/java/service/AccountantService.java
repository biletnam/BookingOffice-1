package service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.*;
import dao.*;

public class AccountantService {
	private ReservationDAO1 reservationDao;
	private TicketDAO1 ticketDao;
	
	public AccountantService(ReservationDAO1 reservationDao, TicketDAO1 ticketDao) {
		super();
		this.reservationDao = reservationDao;
		this.ticketDao = ticketDao;
	}

	List<Reservation> getActualReservation() {
		return reservationDao.getActualReservations();
	}
	
	void confirmPayments(List<Reservation> listR) {
		for (Reservation r : listR) {
			int id = r.getId();
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			Timestamp now = (Timestamp) gc.getTime();
			//TODO r.setDatePayment(now); set to all tickets of reservation datePayment
			reservationDao.update(r);
			List<Ticket> listT = ticketDao.getTicketsForReservation(r);
			for (Ticket t : listT) {
				t.setReservationId(id);
				t.setStatus(TicketStatus.SOLD);
				ticketDao.update(t);
			}
		}
	}
}
