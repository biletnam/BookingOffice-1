package model;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String customerSurname;
	private String customerName;
	private String customerMiddlename;
	private String customerAddress;
	private Timestamp dateReservation;
	private Timestamp datePayment;
	
	private boolean paid;
	
	public Reservation() {
		super();
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMiddlename() {
		return customerMiddlename;
	}

	public void setCustomerMiddlename(String customerMiddlename) {
		this.customerMiddlename = customerMiddlename;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Timestamp getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Timestamp dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Timestamp getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Timestamp datePayment) {
		this.datePayment = datePayment;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getId() {
		return id;
	}


	
}
