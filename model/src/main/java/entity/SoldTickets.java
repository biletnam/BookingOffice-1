package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SoldTickets {
	@Id
	private int ticketId;
	
	@Temporal(TemporalType.DATE)
	private Date ticketDatePayment;
	private String ticketArrival;
	private double ticketPrice;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getTicketDatePayment() {
		return ticketDatePayment;
	}

	public void setTicketDatePayment(Date ticketDatePayment) {
		this.ticketDatePayment = ticketDatePayment;
	}

	public String getTicketArrival() {
		return ticketArrival;
	}

	public void setTicketArrival(String ticketArrival) {
		this.ticketArrival = ticketArrival;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	

}
