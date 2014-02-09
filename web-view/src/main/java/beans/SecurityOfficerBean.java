package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Account;
import entity.AccountRights;
import entity.AccountRole;

@Named
@ManagedBean(name = "securityOfficerBean", eager = true)
@Scope("request")
public class SecurityOfficerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Account> accounts;
	private Account editedAccount;
	private int currentAccountIndex;
	private List<AccountRights> availableAccountRights;
	private Account createdAccount = new Account();

	public SecurityOfficerBean() {
		super();
		setAccounts(initializeAccounts());
		setAvailableAccountRights(intializeAvailableAccountRights());
	}

	public List<AccountRights> getAvailableAccountRights() {
		return availableAccountRights;
	}

	public void setAvailableAccountRights(
			List<AccountRights> availableAccountRights) {
		this.availableAccountRights = availableAccountRights;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
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

	public int getCurrentAccountIndex() {
		return currentAccountIndex;
	}

	public void setCurrentAccountIndex(int currentAccountIndex) {
		this.currentAccountIndex = currentAccountIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void save() {

	}

	private List<AccountRights> intializeAvailableAccountRights() {
		AccountRights ar11 = new AccountRights();
		ar11.setAccountRole(AccountRole.ADMINISTRATOR);
		AccountRights ar12 = new AccountRights();
		ar12.setAccountRole(AccountRole.ACCOUNTANT);
		AccountRights ar13 = new AccountRights();
		ar13.setAccountRole(AccountRole.ANALITYC);
		
		List<AccountRights> accountRights = new ArrayList<>();
		accountRights.add(ar11);
		accountRights.add(ar12);
		accountRights.add(ar13);
		
		return accountRights;
		
	}
	
	private List<Account> initializeAccounts() {

		Account a1 = new Account();
		a1.setLogin("Login1");
		a1.setPsw("Password1");
		a1.setSurname("Surname1");
		a1.setName("Name1");
		a1.setMiddlename("MIddlename1");
		a1.setActive(true);

		AccountRights ar12 = new AccountRights();
		ar12.setAccount(a1);
		ar12.setAccountRole(AccountRole.ACCOUNTANT);
		

		AccountRights ar13 = new AccountRights();
		ar13.setAccount(a1);
		ar13.setAccountRole(AccountRole.ANALITYC);
		

		List<AccountRights> accr1 = new ArrayList<>();
		accr1.add(ar12);
		accr1.add(ar13);

		a1.setAccountRights(accr1);

		Account a2 = new Account();
		a2.setLogin("Login2");
		a2.setPsw("Password2");
		a2.setSurname("Surname2");
		a2.setName("Name2");
		a2.setMiddlename("Middlename2");
		a2.setActive(true);

		AccountRights ar21 = new AccountRights();
		ar21.setAccount(a2);
		ar21.setAccountRole(AccountRole.ADMINISTRATOR);
		

		List<AccountRights> accr2 = new ArrayList<>();
		accr2.add(ar21);

		a2.setAccountRights(accr2);

		List<Account> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);

		return list;

	}

}
