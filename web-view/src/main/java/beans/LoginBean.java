package beans;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Account;
import service.SecurityOfficerService;

@Named
@Scope("session")
public class LoginBean {
	@Inject
	private SecurityOfficerService securityOfficerService;

	private String login;
	private String psw;
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String checkUser() {
		if (login.equals("officer") && psw.equals("officer")) {
			setAccount(new Account());
			account.setSurname("Security");
			account.setName("officer");
			return "securityOfficer";
		} else {
			try {
				account = securityOfficerService.checkUser(login, psw);
			} catch (Exception e) {

			}

			if (account != null) {
				return account.getAccountRole().getName().toLowerCase();
			} else {
				clearBean();
				return "loginFailed";
			}
		}
	}

	private void clearBean() {
		setLogin(null);
		setPsw(null);
		setAccount(null);
	}

	public String logout() {
		clearBean();
		return "main";
	}

}
