package entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Timestamp dateCreated;
	private String flightNumber;
	private String departure;
	private String arrival;
	private Timestamp dateDeparture;
	private Timestamp dateArrival;
	private int ticketAmount;
	private double ticketPrice;
	
	
	public Flight() {
		super();
	}
	
	public Flight(Timestamp dateCreated, String flightNumber, String departure,
			String arrival, Timestamp dateDeparture, Timestamp dateArrival,
			int ticketAmount, double ticketPrice) {
		super();
		this.dateCreated = dateCreated;
		this.flightNumber = flightNumber;
		this.departure = departure;
		this.arrival = arrival;
		this.dateDeparture = dateDeparture;
		this.dateArrival = dateArrival;
		this.ticketAmount = ticketAmount;
		this.ticketPrice = ticketPrice;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
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
	public Timestamp getDateDeparture() {
		return dateDeparture;
	}
	public void setDateDeparture(Timestamp dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	public Timestamp getDateArrival() {
		return dateArrival;
	}
	public void setDateArrival(Timestamp dateArrival) {
		this.dateArrival = dateArrival;
	}
	public int getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getId() {
		return id;
	}
	
	
}
