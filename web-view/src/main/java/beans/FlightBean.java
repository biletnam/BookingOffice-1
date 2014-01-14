package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "flightBean", eager = true)
@SessionScoped
public class FlightBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Date dateCreated;
	private String flightNumber;
	private String departure;
	private String arrival;
	private Date dateDeparture;
	private Date dateArrival;
	private int ticketCount;
	private int ticketPrice;
	
	public FlightBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public void setDateDeparture(Timestamp dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public Date getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(Timestamp dateArrival) {
		this.dateArrival = dateArrival;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
