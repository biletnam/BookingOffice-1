package beans;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class LoginBean {
	
	private String login;
	private String psw;
	
	public LoginBean() {
		super();
		this.login = null;
		this.psw = null;
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
	
	public String check() {
		if (login.equals("login") && psw.equals("password")) {
			return "loginSuccessful";
		}
		else 
		{
			return "loginFailed";
		}
	}
}
