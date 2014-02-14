package beans;

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
	private List<Reservation> reservations;
	
	@PostConstruct
	public void initialize() {
		setReservations(accountantServise.findActualReservations());
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

}
