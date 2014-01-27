package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class AccountRights {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int accountId;
	private AccountRole accountRole;
	private boolean accountRoleActive;
	
	public AccountRights() {
		super();
	}

	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public AccountRole getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}
	
	public boolean isAccountRoleActive() {
		return accountRoleActive;
	}


	public void setAccountRoleActive(boolean accountRoleActive) {
		this.accountRoleActive = accountRoleActive;
	}


	public int getId() {
		return id;
	}
	
}
