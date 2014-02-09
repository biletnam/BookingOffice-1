package service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.*;
import entity.*;

@Named
public class AccountantService {
	@Inject
	private ReservationDao reservationDao;
	
	@Inject
	private TicketDao ticketDao;

	@Transactional
	List<Reservation> getActualReservation() {
		return reservationDao.getActualReservations();
	}

	@Transactional
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
