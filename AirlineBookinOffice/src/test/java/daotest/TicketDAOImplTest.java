package daotest;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.*;

import dao.DAOFactory;
import dao.FlightDAOImpl;
import dao.ReservationDAOImpl;
import dao.TicketDAOImpl;
import entity.Flight;
import entity.Reservation;
import entity.Ticket;
import entity.TicketStatus;

public class TicketDAOImplTest extends IntegrationTestBase {
	private static TicketDAOImpl ticketDAOImpl;
	private static FlightDAOImpl flightDAOImpl;
	private static ReservationDAOImpl reservationDAOImpl;
	
	@BeforeClass
	public static void getDAO() throws Exception {
		DAOFactory factory = new DAOFactory();
		ticketDAOImpl = factory.getTicketDAOImpl();
		flightDAOImpl = factory.getFlightDAOImpl();
		reservationDAOImpl = factory.getReservationDAOImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		ticketDAOImpl.getEntityManager().close();
		flightDAOImpl.getEntityManager().close();
		reservationDAOImpl.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		Ticket t = new Ticket();
		
		int flightId = 1;
		TicketStatus status = TicketStatus.BOOKED;
		Integer reservationId = 3;
		
		t.setFlightId(flightId);
		t.setStatus(status);
		t.setReservationId(reservationId);
		

		ticketDAOImpl.create(t);
		int id = t.getId();
		Ticket tReaded = ticketDAOImpl.read(id);
		assertTrue(id == tReaded.getId());
		assertTrue(status.equals(tReaded.getStatus()));
		assertTrue(reservationId == tReaded.getReservationId());
		
		ticketDAOImpl.delete(t);
	}

	@Test
	public void testUpdate() {
		Ticket t = ticketDAOImpl.read(1);
		TicketStatus status = TicketStatus.BOOKED;
		t.setStatus(status);
		
		ticketDAOImpl.update(t);
		
		Ticket tReaded = ticketDAOImpl.read(1);
		
		assertTrue(t.getId() == tReaded.getId());
		assertTrue(t.getFlightId() == tReaded.getFlightId());
		assertTrue(t.getStatus().equals(tReaded.getStatus()));
		assertTrue(t.getReservationId().equals(tReaded.getReservationId()));
		
		status = TicketStatus.SOLD;
		t.setStatus(status);
			
		ticketDAOImpl.update(t);
	}

	@Test
	public void testDelete() {
		Ticket t = new Ticket();
		int flightId = 1;
		TicketStatus status = TicketStatus.FREE;
		
		t.setFlightId(flightId);
		t.setStatus(status);
		
		ticketDAOImpl.create(t);

		int id = t.getId();
		
		Ticket tReaded = ticketDAOImpl.read(id);
		ticketDAOImpl.delete(tReaded);
		
		tReaded = ticketDAOImpl.read(id);
		assertTrue(tReaded == null);
	}

	@Test
	public void testRead() {
		Ticket t = ticketDAOImpl.read(2);
		
		assertTrue(t.getId() == 2);
		assertTrue(t.getFlightId() == 1);
		assertTrue(t.getStatus().equals(TicketStatus.SOLD));
		assertTrue(t.getReservationId().equals(1));
	}

	@Test
	public void testUpdateTicketsStatus() {
		Ticket t1 = new Ticket();
		int flightId = 1;
		TicketStatus status = TicketStatus.FREE;
		
		t1.setFlightId(flightId);
		t1.setStatus(status);
		
		ticketDAOImpl.create(t1);
		int id1 = t1.getId();
		Ticket t2 = new Ticket();
		flightId = 2;
		status = TicketStatus.FREE;
		
		t2.setFlightId(flightId);
		t2.setStatus(status);
		
		ticketDAOImpl.create(t2);
		int id2 = t2.getId();
		
		List<Ticket> listT = new ArrayList<>();
		listT.add(t1);
		listT.add(t2);
		
		ticketDAOImpl.updateTicketsStatus(listT, TicketStatus.SOLD);
		
		Ticket tReaded1 = ticketDAOImpl.read(id1);
		Ticket tReaded2 = ticketDAOImpl.read(id2);
		
		assertTrue(tReaded1.getStatus().equals(TicketStatus.SOLD));
		assertTrue(tReaded2.getStatus().equals(TicketStatus.SOLD));
		
		ticketDAOImpl.delete(tReaded1);
		ticketDAOImpl.delete(tReaded2);
		
	}

	@Test
	public void testGetAmountOfTicketsForStatus() {
		Flight f = flightDAOImpl.read(1);
		long amount = ticketDAOImpl.getAmountOfTicketsForStatus(f, TicketStatus.FREE);
		assertTrue(amount == 9);
		
	}

	@Test
	public void testGetTicketsForStatus() {
		Flight f = flightDAOImpl.read(1);
		List<Ticket> listT = ticketDAOImpl.getTicketsForStatus(f, TicketStatus.SOLD);
		assertTrue(listT.size() == 2);
		assertTrue(listT.get(0).getId() == 1);
		assertTrue(listT.get(0).getFlightId() == 1);
		assertTrue(listT.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(listT.get(0).getReservationId() == 1);
	}

	@Test
	public void testGetTicketsForExpiredReservation() {
		List<Ticket> listT = ticketDAOImpl.getTicketsForExpiredReservation();
		assertTrue(listT.size() == 9);
		assertTrue(listT.get(0).getId() == 1);
		assertTrue(listT.get(0).getFlightId() == 1);
		assertTrue(listT.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(listT.get(0).getReservationId() == 1);
	}

	@Test
	public void testGetTicketsForReservation() {
		Reservation r = reservationDAOImpl.read(1);
		
		List<Ticket> listT = ticketDAOImpl.getTicketsForReservation(r);
		assertTrue(listT.size() == 3);
		assertTrue(listT.get(0).getId() == 1);
		assertTrue(listT.get(0).getFlightId() == 1);
		assertTrue(listT.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(listT.get(0).getReservationId() == 1);
	}

	@Test
	public void testSelectDailyDataInTotal() {
		// TODO
	}

	@Test
	public void testSelectDailyDataByArrivalPlace() {
		GregorianCalendar gcStart = new GregorianCalendar(2013, Calendar.DECEMBER, 01, 0, 0, 0);
		Timestamp startDate = new java.sql.Timestamp(gcStart.getTime().getTime());
		GregorianCalendar gcEnd = new GregorianCalendar(2013, Calendar.DECEMBER, 5, 0, 0, 0);
		Timestamp endDate = new java.sql.Timestamp(gcEnd.getTime().getTime());
		ticketDAOImpl.selectDailyDataByArrivalPlace(startDate, endDate);
	}

}
