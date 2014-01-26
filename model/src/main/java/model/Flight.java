package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	private String flightNumber;
	private String departure;
	private String arrival;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeparture;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrival;
	private int ticketAmount;
	private int ticketFreeAmount;
	private double ticketPrice;
	
	
	public Flight() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public Date getDateDeparture() {
		return dateDeparture;
	}


	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}


	public Date getDateArrival() {
		return dateArrival;
	}


	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}


	public int getTicketAmount() {
		return ticketAmount;
	}


	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}


	public int getTicketFreeAmount() {
		return ticketFreeAmount;
	}


	public void setTicketFreeAmount(int ticketFreeAmount) {
		this.ticketFreeAmount = ticketFreeAmount;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}


	

	
}
