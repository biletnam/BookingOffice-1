package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AccountantService;
import entity.Reservation;

@Named
@Scope("request")
public class AccountantBean {

	@Inject
	private AccountantService accountantServise;

	private static final long serialVersionUID = 1L;
	private List<Reservation> reservations = new ArrayList<>();
	private int currentReservationIndex;
	private Reservation editedReservation = new Reservation();

	@PostConstruct
	public void init() {
		setReservations(accountantServise.findActualReservations());
	}
	
	public void saveReservation() {
		accountantServise.updateReservation(editedReservation);
	}
	
	public void findActualReservations() {
		setReservations(accountantServise.findActualReservations());
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation getEditedReservation() {
		return editedReservation;
	}

	public void setEditedReservation(Reservation editedReservation) {
		this.editedReservation = editedReservation;
	}

	public int getCurrentReservationIndex() {
		return currentReservationIndex;
	}

	public void setCurrentReservationIndex(int currentReservationIndex) {
		this.currentReservationIndex = currentReservationIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
