package service;

import java.util.List;

import model.*;
import dao.*;

public class AccountantService {
	private ReservationDao reservationDao;
	private TicketDao ticketDao;

	List<Reservation> getActualReservation() {
		return reservationDao.getActualReservations();
	}

	void updateReservation(Reservation r) {
		reservationDao.update(r);
		List<Ticket> listT = ticketDao.getTicketsForReservation(r);
		for (Ticket t : listT) {
			t.setReservationId(r.getId());
			t.setStatus(TicketStatus.SOLD);
			ticketDao.update(t);
		}
	}

}
