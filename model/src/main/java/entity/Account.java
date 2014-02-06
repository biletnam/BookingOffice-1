package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String login;
	private String psw;
	private String surname;
	private String name;
	private String middlename;
	private boolean active;
	@OneToMany(mappedBy = "account")
	private List<AccountRights> accountRights;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public List<AccountRights> getAccountRights() {
		return accountRights;
	}

	public void setAccountRights(List<AccountRights> accountRights) {
		this.accountRights = accountRights;
	}

}
