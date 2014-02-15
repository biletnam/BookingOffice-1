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
}
