package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Account;

@ManagedBean(name = "securityOfficer", eager = true)
@SessionScoped
public class SecurityOfficerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Account> listAccounts;
	private ArrayList<String> listAccountRights;
	
	public SecurityOfficerBean() {
		super();
		setListAccounts(initializeListAccounts());
	}

	public ArrayList<Account> getListAccounts() {
		return listAccounts;
	}

	public void setListAccounts(ArrayList<Account> listAccounts) {
		this.listAccounts = listAccounts;
	}

	public ArrayList<String> getListAccountRights() {
		return listAccountRights;
	}

	public void setListAccountRights(ArrayList<String> listAccountRights) {
		this.listAccountRights = listAccountRights;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private ArrayList<Account> initializeListAccounts() {
		
		Account a1 = new Account();
		a1.setLogin("Login1");
		a1.setPsw("Password1");
		a1.setSurname("Surname1");
		a1.setName("Name1");
		a1.setMiddlename("MIddlename1");
		a1.setIsActive(true);
		
		Account a2 = new Account();
		a2.setLogin("Login2");
		a2.setPsw("Password2");
		a2.setSurname("Surname2");
		a2.setName("Name2");
		a2.setMiddlename("Middlename2");
		a2.setIsActive(true);
		
		
		ArrayList<Account> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);
		
		return list;
		
	}

}
