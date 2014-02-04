package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class AccountRights implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account account;
	private AccountRole accountRole;
	private boolean accountRoleActive;
	
	public boolean isAccountRoleActive() {
		return accountRoleActive;
	}

	public void setAccountRoleActive(boolean accountRoleActive) {
		this.accountRoleActive = accountRoleActive;
	}

	public AccountRights() {
		super();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountRole getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

	public int getId() {
		return id;
	}

}
