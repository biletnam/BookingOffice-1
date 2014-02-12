package beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
