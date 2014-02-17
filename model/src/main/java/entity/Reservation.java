package entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String customerSurname;
	private String customerName;
	private String customerMiddlename;
	private String customerEmail;
	private double sumTotal;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReservation;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePayment;
	private boolean paid;

	@PrePersist
    protected void onCreate() {
		dateReservation = Calendar.getInstance().getTime();
    }
	
	public double getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(double sumTotal) {
		this.sumTotal = sumTotal;
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

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
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
