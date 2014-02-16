package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.AccountRole;

@SuppressWarnings("serial")
@Named
@Scope("session")
public class BackingBean implements Serializable {

	private int reservationId;
	private int accountId;
	private AccountRole[] accountRoles = AccountRole.values();
	private int flightId;
	private String flightNumberFilter;

	@PostConstruct
	public void initialize() {

	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public AccountRole[] getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(AccountRole[] accountRoles) {
		this.accountRoles = accountRoles;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumberFilter() {
		return flightNumberFilter;
	}

	public void setFlightNumberFilter(String flightNumberFilter) {
		this.flightNumberFilter = flightNumberFilter;
	}

}
