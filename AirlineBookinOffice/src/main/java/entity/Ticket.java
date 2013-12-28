package entity;

import javax.persistence.*;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int flightId;
	private TicketStatus status;
	private Integer reservationId; //TODO type?
	
	
	public Ticket() {
		super();
	}


	public Ticket(int flightId, TicketStatus status) {
		super();
		this.flightId = flightId;
		this.status = status;
	}


	public int getFlightId() {
		return flightId;
	}


	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}


	public TicketStatus getStatus() {
		return status;
	}


	public void setStatus(TicketStatus status) {
		this.status = status;
	}


	public Integer getReservationId() {
		return reservationId;
	}


	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}


	public int getId() {
		return id;
	}

	
}
