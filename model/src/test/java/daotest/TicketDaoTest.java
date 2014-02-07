package daotest;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.*;

import dao.FlightDao;
import dao.ReservationDao;
import dao.TicketDao;
import entity.DataForReport;
import entity.Flight;
import entity.Reservation;
import entity.Ticket;
import entity.TicketStatus;

public class TicketDaoTest extends TestBase {
	private static TicketDao ticketDao;
	private static FlightDao flightDao;
	private static ReservationDao reservationDao;

	@BeforeClass
	public static void getDAO() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		ticketDao = new TicketDao();
		EntityManager entityManager1 = factory.createEntityManager();
		ticketDao.setEntityManager(entityManager1);
		flightDao = new FlightDao();
		EntityManager entityManager2 = factory.createEntityManager();
		flightDao.setEntityManager(entityManager2);
		reservationDao = new ReservationDao();
		EntityManager entityManager3 = factory.createEntityManager();
		reservationDao.setEntityManager(entityManager3);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		ticketDao.getEntityManager().close();
		flightDao.getEntityManager().close();
		reservationDao.getEntityManager().close();
	}

	@Test
	public void testCreate() {
		Ticket t = new Ticket();

		int flightId = 1;
		TicketStatus status = TicketStatus.BOOKED;
		Integer reservationId = 3;
		GregorianCalendar gcPayment = new GregorianCalendar(2013,
				Calendar.DECEMBER, 10, 4, 40, 0);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime()
				.getTime());

		t.setFlightId(flightId);
		t.setStatus(status);
		t.setReservationId(reservationId);
		t.setDatePayment(dtPayment);

		ticketDao.create(t);
		int id = t.getId();
		Ticket tReaded = ticketDao.read(id);
		assertTrue(id == tReaded.getId());
		assertTrue(status.equals(tReaded.getStatus()));
		assertTrue(reservationId == tReaded.getReservationId());
		assertTrue(dtPayment.equals(tReaded.getDatePayment()));

		ticketDao.delete(id);
	}

	@Test
	public void testUpdate() {
		Ticket t = ticketDao.read(1);
		TicketStatus status = TicketStatus.BOOKED;
		t.setStatus(status);

		ticketDao.update(t);

		Ticket tReaded = ticketDao.read(1);

		assertTrue(t.getId() == tReaded.getId());
		assertTrue(t.getFlightId() == tReaded.getFlightId());
		assertTrue(t.getStatus().equals(tReaded.getStatus()));
		assertTrue(t.getReservationId() == tReaded.getReservationId());
		assertTrue(t.getDatePayment().equals(tReaded.getDatePayment()));

		status = TicketStatus.SOLD;
		t.setStatus(status);

		ticketDao.update(t);
	}

	@Test
	public void testDelete() {
		Ticket t = new Ticket();
		int flightId = 1;
		TicketStatus status = TicketStatus.BOOKED;
		GregorianCalendar gcPayment = new GregorianCalendar(2013,
				Calendar.DECEMBER, 23, 4, 40, 0);
		Date dtPayment = gcPayment.getTime();

		t.setFlightId(flightId);
		t.setStatus(status);
		t.setReservationId(3);
		t.setDatePayment(dtPayment);
		// TODO Reservation

		ticketDao.create(t);

		int id = t.getId();

		Ticket tReaded = ticketDao.read(id);
		ticketDao.delete(id);

		tReaded = ticketDao.read(id);
		assertTrue(tReaded == null);
	}

	@Test
	public void testRead() {
		Ticket t = ticketDao.read(2);
		GregorianCalendar gcPayment = new GregorianCalendar(2013,
				Calendar.DECEMBER, 5, 10, 00, 14);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime()
				.getTime());

		assertTrue(t.getId() == 2);
		assertTrue(t.getFlightId() == 1);
		assertTrue(t.getStatus().equals(TicketStatus.SOLD));
		assertTrue(t.getReservationId() == 1);
		assertTrue(t.getDatePayment().equals(dtPayment));
	}

	@Test
	public void testUpdateTicketsStatus() {
		Ticket t1 = new Ticket();
		int flightId = 1;
		TicketStatus status = TicketStatus.BOOKED;

		t1.setFlightId(flightId);
		t1.setReservationId(3);
		t1.setStatus(status);

		ticketDao.create(t1);
		int id1 = t1.getId();
		Ticket t2 = new Ticket();
		flightId = 2;
		status = TicketStatus.BOOKED;

		t2.setFlightId(flightId);
		t2.setReservationId(3);
		t2.setStatus(status);

		ticketDao.create(t2);
		int id2 = t2.getId();

		List<Ticket> tickets = new ArrayList<>();
		tickets.add(t1);
		tickets.add(t2);

		ticketDao.updateTicketsStatus(tickets, TicketStatus.SOLD);

		Ticket tReaded1 = ticketDao.read(id1);
		Ticket tReaded2 = ticketDao.read(id2);

		assertTrue(tReaded1.getStatus().equals(TicketStatus.SOLD));
		assertTrue(tReaded2.getStatus().equals(TicketStatus.SOLD));

		ticketDao.delete(id1);
		ticketDao.delete(id2);

	}

	@Test
	public void testGetAmountOfTicketsForStatusForTheFlight() {
		Flight f = flightDao.read(1);
		long amount = ticketDao.getAmountOfTicketsForStatusForTheFlight(f,
				TicketStatus.BOOKED);
		assertTrue(amount == 2);

	}

	@Test
	public void testGetTicketsForStatusForTheFlight() {
		Flight f = flightDao.read(1);
		List<Ticket> tickets = ticketDao.getTicketsForStatusForTheFlight(f,
				TicketStatus.SOLD);
		assertTrue(tickets.size() == 2);
		assertTrue(tickets.get(0).getId() == 1);
		assertTrue(tickets.get(0).getFlightId() == 1);
		assertTrue(tickets.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(tickets.get(0).getReservationId() == 1);
	}

	@Test
	public void testGetTicketsForExpiredReservation() {
		List<Ticket> tickets = ticketDao.getTicketsForExpiredReservation();
		assertTrue(tickets.size() == 9);
		assertTrue(tickets.get(0).getId() == 1);
		assertTrue(tickets.get(0).getFlightId() == 1);
		assertTrue(tickets.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(tickets.get(0).getReservationId() == 1);
	}

	@Test
	public void testGetTicketsForReservation() {
		Reservation r = reservationDao.read(1);

		List<Ticket> tickets = ticketDao.getTicketsForReservation(r);
		assertTrue(tickets.size() == 3);
		assertTrue(tickets.get(0).getId() == 1);
		assertTrue(tickets.get(0).getFlightId() == 1);
		assertTrue(tickets.get(0).getStatus().equals(TicketStatus.SOLD));
		assertTrue(tickets.get(0).getReservationId() == 1);
	}

	@Test
	public void testSelectDataByDay() {
		GregorianCalendar gcStart = new GregorianCalendar(2013,
				Calendar.DECEMBER, 01, 0, 0, 0);
		Date startDate = gcStart.getTime();
		GregorianCalendar gcEnd = new GregorianCalendar(2013,
				Calendar.DECEMBER, 5, 0, 0, 0);
		Date endDate = gcEnd.getTime();
		List<DataForReport> data = ticketDao.selectDataByDay(startDate, endDate);
		assertTrue(data.size() == 1);
		assertTrue(data.get(0).getTicketCount() == 3);
		assertTrue(data.get(0).getTicketSum() == 3500);
		
	}

	@Test
	public void testSelectDataByArrivalPlace() {
		GregorianCalendar gcStart = new GregorianCalendar(2013,
				Calendar.DECEMBER, 01, 0, 0, 0);
		Date startDate = gcStart.getTime();
		GregorianCalendar gcEnd = new GregorianCalendar(2013,
				Calendar.DECEMBER, 5, 0, 0, 0);
		Date endDate = gcEnd.getTime();
		List<DataForReport> data = ticketDao.selectDataByArrivalPlace(startDate, endDate);
		assertTrue(data.size() == 2);
		assertTrue(data.get(0).getTicketCount() == 1);
		assertTrue(data.get(0).getTicketSum() == 1500);
	}

}
