package model;

import javax.persistence.*;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String login;
	private String psw;
	private String surname;
	private String name;
	private String middlename;
	private boolean isActive;
	
	public Account() {
		super();
	}

	public Account(String login, String psw, String surname, String name,
			String middlename, boolean isActive) {
		super();
		this.login = login;
		this.psw = psw;
		this.surname = surname;
		this.name = name;
		this.middlename = middlename;
		this.isActive = isActive;
	}

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
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}
	
}
