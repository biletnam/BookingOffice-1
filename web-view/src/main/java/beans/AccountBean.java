package beans;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "accountBean", eager = true)
@SessionScoped
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String login;
	private String psw;
	private String surname;
	private String name;
	private String middlename;
	private boolean isActive;
	private String [] accountRights;
	
	public AccountBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String[] getAccountRights() {
		return accountRights;
	}

	public void setAccountRights(String[] accountRights) {
		this.accountRights = accountRights;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	public String accountRightsToString() {
		return Arrays.toString(accountRights);
	}

}
