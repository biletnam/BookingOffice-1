package entity;

import java.util.Date;

public class DataForReport {

	private Date datePayment;
	private String arrival;
	private long ticketCount;
	private double ticketSum;

	public DataForReport() {

	}

	public DataForReport(Date datePayment, long ticketCount, double ticketSum) {
		super();
		this.datePayment = datePayment;
		this.ticketCount = ticketCount;
		this.ticketSum = ticketSum;
	}

	public DataForReport(String arrival, long ticketCount, double ticketSum) {
		super();
		this.arrival = arrival;
		this.ticketCount = ticketCount;
		this.ticketSum = ticketSum;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public long getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(long ticketCount) {
		this.ticketCount = ticketCount;
	}

	public double getTicketSum() {
		return ticketSum;
	}

	public void setTicketSum(double ticketSum) {
		this.ticketSum = ticketSum;
	}

}
