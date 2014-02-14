package beans;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AccountantService;
import entity.Reservation;

@Named
@Scope("request")
public class ReservationBean {
	@Inject
	private AccountantService accountantService;
	private Reservation reservation;

	@PostConstruct
	public void init() {
		reservation = new Reservation();
	}
	
	public void saveReservation() {
		accountantService.updateReservation(reservation);
	}
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public AccountantService getAccountantService() {
		return accountantService;
	}

	public void setAccountantService(AccountantService accountantService) {
		this.accountantService = accountantService;
	}
	
	

}
