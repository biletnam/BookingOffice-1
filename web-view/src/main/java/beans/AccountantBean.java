package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Reservation;

@ManagedBean(name = "accountant", eager = true)
@SessionScoped
public class AccountantBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Reservation> listOverdueReservations;
	
	public AccountantBean() {
		super();
		setListOverdueReservations(initializeListOverdueReservations());
	}

	public ArrayList<Reservation> getListOverdueReservations() {
		return listOverdueReservations;
	}

	public void setListOverdueReservations(
			ArrayList<Reservation> listOverdueReservations) {
		this.listOverdueReservations = listOverdueReservations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private ArrayList<Reservation> initializeListOverdueReservations() {

		Reservation r1 = new Reservation();
		r1.setCustomerSurname("Surname1");
		r1.setCustomerName("Name1");
		r1.setCustomerMiddlename("MiddleName1");
		r1.setCustomerAddress("Address1");

		GregorianCalendar gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 04, 10, 0, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());

		r1.setDateReservation(dtReservation);
		r1.setIsPaid(false);
		
		Reservation r2 = new Reservation();
		r2.setCustomerSurname("Surname2");
		r2.setCustomerName("Name2");
		r2.setCustomerMiddlename("MiddleName2");
		r2.setCustomerAddress("Address2");

		gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 05, 10, 0, 14);
		dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());

		r2.setDateReservation(dtReservation);
		r2.setIsPaid(false);

		

		ArrayList<Reservation> list = new ArrayList<Reservation>();
		list.add(r1);
		list.add(r2);

		return list;
	}
}
