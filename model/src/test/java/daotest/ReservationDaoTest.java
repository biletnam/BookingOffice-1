package daotest;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.*;

import dao.ReservationDao;
import entity.Reservation;

public class ReservationDaoTest extends TestBase {

	private static ReservationDao reservationDao;

	@BeforeClass
	public static void getDAO() throws Exception {
		reservationDao = new ReservationDao();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		EntityManager entityManager = factory.createEntityManager();
		reservationDao.setEntityManager(entityManager);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		reservationDao.getEntityManager().close();
	}

	@Test
	public void testCreate() {
		Reservation r = new Reservation();

		String customerSurname = "Surname4";
		String customerName = "Name4";
		String customerMiddlename = "Middlename4";
		String customerAddress = "Address4";

		GregorianCalendar gcReservation = new GregorianCalendar(2013,
				Calendar.DECEMBER, 10, 4, 37, 0);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation
				.getTime().getTime());
		boolean paid = true;

		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerEmail(customerAddress);
		r.setDateReservation(dtReservation);
		r.setPaid(paid);

		reservationDao.create(r);
		int id = r.getId();
		Reservation rReaded = reservationDao.read(id);
		assertTrue(id == rReaded.getId());
		assertTrue(customerSurname.equals(rReaded.getCustomerSurname()));
		assertTrue(customerName.equals(rReaded.getCustomerName()));
		assertTrue(customerMiddlename.equals(rReaded.getCustomerMiddlename()));
		assertTrue(customerAddress.equals(rReaded.getCustomerEmail()));

		assertTrue(dtReservation.equals(rReaded.getDateReservation()));
		assertTrue(paid == rReaded.isPaid());

		reservationDao.delete(id);
	}

	@Test
	public void testUpdate() {
		Reservation r = reservationDao.read(1);
		String customerSurname = "Surname11";
		r.setCustomerSurname(customerSurname);

		reservationDao.update(r);

		Reservation rReaded = reservationDao.read(1);

		assertTrue(r.getId() == rReaded.getId());
		assertTrue(customerSurname.equals(rReaded.getCustomerSurname()));
		assertTrue(r.getCustomerName().equals(rReaded.getCustomerName()));
		assertTrue(r.getCustomerMiddlename().equals(
				rReaded.getCustomerMiddlename()));
		assertTrue(r.getCustomerEmail().equals(rReaded.getCustomerEmail()));

		assertTrue(r.getDateReservation().equals(rReaded.getDateReservation()));
		assertTrue(r.isPaid() == rReaded.isPaid());

		customerSurname = "Surname1";
		r.setCustomerSurname(customerSurname);
		reservationDao.update(r);
	}

	@Test
	public void testDelete() {
		Reservation r = new Reservation();

		String customerSurname = "Surname4";
		String customerName = "Name4";
		String customerMiddlename = "Middlename4";
		String customerAddress = "Address4";

		GregorianCalendar gcReservation = new GregorianCalendar(2013,
				Calendar.DECEMBER, 23, 4, 37, 0);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation
				.getTime().getTime());
		boolean paid = true;

		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerEmail(customerAddress);
		r.setDateReservation(dtReservation);
		r.setPaid(paid);

		reservationDao.create(r);

		int id = r.getId();

		Reservation rReaded = reservationDao.read(id);
		reservationDao.delete(id);

		rReaded = reservationDao.read(id);
		assertTrue(rReaded == null);
	}

	@Test
	public void testRead() {
		Reservation r = reservationDao.read(2);

		GregorianCalendar gcReservation = new GregorianCalendar(2013,
				Calendar.DECEMBER, 5, 10, 00, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation
				.getTime().getTime());
		boolean paid = true;

		assertTrue(r.getId() == 2);
		assertTrue(r.getCustomerSurname().equals("Surname2"));
		assertTrue(r.getCustomerName().equals("Name2"));
		assertTrue(r.getCustomerMiddlename().equals("Middlename2"));
		assertTrue(r.getCustomerEmail().equals("Address2"));

		assertTrue(r.getDateReservation().equals(dtReservation));
		assertTrue(r.isPaid() == paid);
	}

	@Test
	public void testGetExpiredReservations() {
		List<Reservation> reservations = reservationDao
				.getExpiredReservations();
		GregorianCalendar gcReservation = new GregorianCalendar(2013,
				Calendar.DECEMBER, 6, 10, 00, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation
				.getTime().getTime());

		boolean paid = false;

		assertTrue(reservations.size() == 1);
		assertTrue(reservations.get(0).getId() == 3);
		assertTrue(reservations.get(0).getCustomerSurname().equals("Surname3"));
		assertTrue(reservations.get(0).getCustomerName().equals("Name3"));
		assertTrue(reservations.get(0).getCustomerMiddlename()
				.equals("Middlename3"));
		assertTrue(reservations.get(0).getCustomerEmail().equals("Address3"));
		assertTrue(reservations.get(0).getDateReservation()
				.equals(dtReservation));
		assertTrue(reservations.get(0).isPaid() == paid);

	}

	@Test
	public void testGetActualReservations() {
		Reservation r = new Reservation();

		String customerSurname = "Surname6";
		String customerName = "Name6";
		String customerMiddlename = "Middlename6";
		String customerAddress = "Address6";

		Timestamp dtReservation = new java.sql.Timestamp(Calendar.getInstance()
				.getTime().getTime());
		boolean paid = true;

		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerEmail(customerAddress);
		r.setDateReservation(dtReservation);
		r.setPaid(paid);

		reservationDao.create(r);

		List<Reservation> reservations = reservationDao.getActualReservations();
		assertTrue(reservations.size() == 1);
		assertTrue(reservations.get(0).getId() == 4);
		assertTrue(reservations.get(0).getCustomerSurname().equals("Surname6"));
		assertTrue(reservations.get(0).getCustomerName().equals("Name6"));
		assertTrue(reservations.get(0).getCustomerMiddlename()
				.equals("Middlename6"));
		assertTrue(reservations.get(0).getCustomerEmail().equals("Address6"));
		assertTrue(reservations.get(0).getDateReservation()
				.equals(dtReservation));
		assertTrue(reservations.get(0).isPaid() == paid);

	}

}
