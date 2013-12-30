package service;

import java.util.List;

import model.*;
import dao.FlightDAO;
import dao.ReservationDAO;
import dao.TicketDAO;

public class AdministratorService {
	private FlightDAO flightDao;
	private TicketDAO ticketDao;
	private ReservationDAO reservationDao;
	
	public AdministratorService(FlightDAO flightDao, TicketDAO ticketDao,
			ReservationDAO reservationDao) {
		super();
		this.flightDao = flightDao;
		this.ticketDao = ticketDao;
		this.reservationDao = reservationDao;
	}

	void addFlightToTheTimeTable(Flight f) {
		int ticketAmount = f.getTicketAmount();
		flightDao.create(f);
		for (int i = 0; i < ticketAmount; i++)
		{
			Ticket t = new Ticket();
			t.setFlightId(f.getId());
			t.setStatus(TicketStatus.FREE);
			ticketDao.create(t);
			//TODO check dates
		}
	}
	
	void editFlightAmountOfTickets(Flight f) {
		int ticketAmountNew = f.getTicketAmount();
		int id = f.getId();
		int ticketAmount = flightDao.read(id).getTicketAmount();
		
		if (ticketAmountNew > ticketAmount) {
			for (int i = 0; i < ticketAmountNew - ticketAmount; i++)
			{
				Ticket t = new Ticket();
				t.setFlightId(f.getId());
				t.setStatus(TicketStatus.FREE);
				ticketDao.create(t);
			}
		}
		else if (ticketAmountNew < ticketAmount) {
			long amountForDelete = ticketAmount - ticketAmountNew;
			long amountFree = ticketDao.getAmountOfTicketsForStatus(f, TicketStatus.FREE);
			if (amountForDelete > amountFree) {
				//TODO message for deleting flight
				return;
			} else if (amountForDelete < amountFree) {
				List<Ticket> listT = ticketDao.getTicketsForStatus(f, TicketStatus.FREE);
				int count = 0;
				for (Ticket t : listT) {
					ticketDao.delete(t);
					count++;
					if (count == amountFree - amountForDelete) {
						break;
					}
				}
				
			} else if (amountForDelete == amountFree)
			{
				//TODO message for changing flight
				return;
			}
		} else if (ticketAmountNew == ticketAmount)
		{
			//TODO message for changing flight
			return; 
		}
		
	}
	
	void deleteFlightFromTimeTable(Flight f) {
		long amountUsed = ticketDao.getAmountOfTicketsForStatus(f, TicketStatus.BOOKED) + ticketDao.getAmountOfTicketsForStatus(f, TicketStatus.SOLD);
		if (amountUsed == 0) {
			flightDao.delete(f);
		} else 
		{
			//TODO message for deleting flight
		}
	}
	
	void convertAllBookedTicketsMoreThanThreeDaysAgo() {
		List<Ticket> listT= ticketDao.getTicketsForExpiredReservation();
		for (Ticket t : listT) {
			t.setReservationId(0);
			t.setStatus(TicketStatus.FREE);
			ticketDao.update(t);
		}
		List<Reservation> listR = reservationDao.getExpiredReservations();
		for (Reservation r : listR) {
			reservationDao.delete(r);
		}
		
	}
}
