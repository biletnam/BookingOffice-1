package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Account;

@ManagedBean(name = "securityOfficerBean", eager = true)
@SessionScoped
public class SecurityOfficerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Account> accounts;
	private Account editedAccount;
	private List<String> editedAccountRights;
	private Account createdAccount = new Account();
	
	public SecurityOfficerBean() {
		super();
		setAccounts(initializeListAccounts());
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<String> getEditedAccountRights() {
		return editedAccountRights;
	}

	public void setEditedAccountRights(List<String> editedAccountRights) {
		this.editedAccountRights = editedAccountRights;
	}

	public Account getEditedAccount() {
		return editedAccount;
	}

	public void setEditedAccount(Account editedAccount) {
		this.editedAccount = editedAccount;
	}

	public Account getCreatedAccount() {
		return createdAccount;
	}

	public void setCreatedAccount(Account createdAccount) {
		this.createdAccount = createdAccount;
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
