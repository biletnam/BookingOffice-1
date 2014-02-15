package daotest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.*;

import dao.*;
import entity.Flight;

public class FlightDaoTest extends TestBase {
	private static FlightDao flightDao;
		
	@BeforeClass
	public static void getDAO() throws Exception {
		flightDao = new FlightDao();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		EntityManager entityManager = factory.createEntityManager();
		flightDao.setEntityManager(entityManager);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		flightDao.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		Flight f = new Flight();
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 22, 9, 44, 0);
		Timestamp dtCreated = new java.sql.Timestamp(gcCreated.getTime().getTime());
		
		String flightNumber = "PF-143";
		
		String departure = "Kyiv";
		
		String arrival = "London";
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 50, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 5, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		int ticketAmount = 3;
		
		double ticketPrice = 3000;
		f.setDateCreated(dtCreated);
		f.setFlightNumber(flightNumber);
		f.setDeparture(departure);
		f.setArrival(arrival);
		f.setDateDeparture(dtDeparture);
		f.setDateArrival(dtArrival);
		f.setTicketAmount(ticketAmount);
		f.setTicketPrice(ticketPrice);

		flightDao.getEntityManager().getTransaction().begin();
		flightDao.create(f);
		flightDao.getEntityManager().getTransaction().commit();
		
		int id = f.getId();
		Flight fReaded = flightDao.read(id);
		assertTrue(id == fReaded.getId());
		assertTrue(dtCreated.equals(fReaded.getDateCreated()));
		assertTrue(flightNumber.equals(fReaded.getFlightNumber()));
		assertTrue(departure.equals(fReaded.getDeparture()));
		assertTrue(arrival.equals(fReaded.getArrival()));
		assertTrue(dtDeparture.equals(fReaded.getDateDeparture()));
		assertTrue(dtArrival.equals(fReaded.getDateArrival()));
		assertTrue(ticketAmount == fReaded.getTicketAmount());
		assertTrue(ticketPrice == fReaded.getTicketPrice());
		
		flightDao.getEntityManager().getTransaction().begin();
		flightDao.delete(id);
		flightDao.getEntityManager().getTransaction().commit();
	}

	@Test
	public void testUpdate() {
		Flight f = flightDao.read(1);
		int ticketAmount = 20;
		f.setTicketAmount(ticketAmount);
		
		flightDao.getEntityManager().getTransaction().begin();
		flightDao.update(f);
		flightDao.getEntityManager().getTransaction().commit();
		
		Flight fReaded = flightDao.read(1);
		
		assertTrue(f.getId() == fReaded.getId());
		assertTrue(f.getDateCreated().equals(fReaded.getDateCreated()));
		assertTrue(f.getFlightNumber().equals(fReaded.getFlightNumber()));
		assertTrue(f.getDeparture().equals(fReaded.getDeparture()));
		assertTrue(f.getArrival().equals(fReaded.getArrival()));
		assertTrue(f.getDateDeparture().equals(fReaded.getDateDeparture()));
		assertTrue(f.getDateArrival().equals(fReaded.getDateArrival()));
		assertTrue(ticketAmount == fReaded.getTicketAmount());
		assertTrue(f.getTicketPrice() == fReaded.getTicketPrice());	
		
		ticketAmount = 10;
		f.setTicketAmount(ticketAmount);
		
		flightDao.getEntityManager().getTransaction().begin();
		flightDao.update(f);
		flightDao.getEntityManager().getTransaction().commit();
	}

	@Test
	public void testDelete() {
		Flight f = new Flight();
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 22, 9, 44, 0);
		Timestamp dtCreated = new java.sql.Timestamp(gcCreated.getTime().getTime());
		
		String flightNumber = "PF-143";
		
		String departure = "Kyiv";
		
		String arrival = "London";
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 50, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 5, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		int ticketAmount = 3;
		
		double ticketPrice = 3000;
		f.setDateCreated(dtCreated);
		f.setFlightNumber(flightNumber);
		f.setDeparture(departure);
		f.setArrival(arrival);
		f.setDateDeparture(dtDeparture);
		f.setDateArrival(dtArrival);
		f.setTicketAmount(ticketAmount);
		f.setTicketPrice(ticketPrice);

		flightDao.getEntityManager().getTransaction().begin();
		flightDao.create(f);
		flightDao.getEntityManager().getTransaction().commit();

		int id = f.getId();
		
		Flight fReaded = flightDao.read(id);
		
		flightDao.getEntityManager().getTransaction().begin();
		flightDao.delete(id);
		flightDao.getEntityManager().getTransaction().commit();
		
		fReaded = flightDao.read(id);
		assertTrue(fReaded == null);
	}

	@Test
	public void testRead() {
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 01, 10, 20, 0);
		Date dtCreated = gcCreated.getTime();
		
		Flight f = flightDao.read(2);
		assertTrue(f.getId() == 2);
		assertTrue(dtCreated.equals(f.getDateCreated()));
		assertTrue(f.getFlightNumber().equals("AQ-021"));
		assertTrue(f.getDeparture().equals("Kyiv"));
		assertTrue(f.getArrival().equals("Roma"));
		assertTrue(f.getTicketAmount() == 5);
		assertTrue(f.getTicketPrice() == 1500);
	}

	@Test
	public void testFind() {
		String arrival = "New York";
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 28);
		java.sql.Date dtDeparture = new java.sql.Date(gcDeparture.getTime().getTime());
		List<Flight> flights = flightDao.findByArrivalAndDepartureDate(arrival, dtDeparture);
		assertTrue(flights.size() == 1);
	}

}
