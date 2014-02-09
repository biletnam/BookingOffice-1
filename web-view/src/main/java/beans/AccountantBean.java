package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Reservation;

@Named
@ManagedBean(name = "accountantBean", eager = true)
@Scope("request")
public class AccountantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Reservation> reservations;
	private int currentReservationIndex;
	private Reservation editedReservation;
	
	public AccountantBean() {
		super();
		setReservations(initializeReservations());
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation getEditedReservation() {
		return editedReservation;
	}

	public void setEditedReservation(Reservation editedReservation) {
		this.editedReservation = editedReservation;
	}

	public int getCurrentReservationIndex() {
		return currentReservationIndex;
	}

	public void setCurrentReservationIndex(int currentReservationIndex) {
		this.currentReservationIndex = currentReservationIndex;
	}

	public void save() {

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private List<Reservation> initializeReservations() {

		Reservation r1 = new Reservation();
		r1.setCustomerSurname("Surname1");
		r1.setCustomerName("Name1");
		r1.setCustomerMiddlename("MiddleName1");
		r1.setCustomerEmail("Address1");

		GregorianCalendar gcReservation = new GregorianCalendar(2013,
				Calendar.DECEMBER, 04, 10, 0, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation
				.getTime().getTime());

		r1.setDateReservation(dtReservation);
		r1.setPaid(false);

		Reservation r2 = new Reservation();
		r2.setCustomerSurname("Surname2");
		r2.setCustomerName("Name2");
		r2.setCustomerMiddlename("MiddleName2");
		r2.setCustomerEmail("Address2");

		gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 05, 10,
				0, 14);
		dtReservation = new java.sql.Timestamp(gcReservation.getTime()
				.getTime());

		r2.setDateReservation(dtReservation);
		r2.setPaid(false);

		List<Reservation> list = new ArrayList<Reservation>();
		list.add(r1);
		list.add(r2);

		return list;
	}
}
