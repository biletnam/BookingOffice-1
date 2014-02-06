package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int flightId;
	private int reservationId;
	private TicketStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePayment;

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

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public int getId() {
		return id;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
}
