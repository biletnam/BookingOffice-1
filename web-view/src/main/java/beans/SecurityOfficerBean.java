package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Account;
import model.AccountRights;
import model.AccountRole;

@ManagedBean(name = "securityOfficerBean", eager = true)
@SessionScoped
public class SecurityOfficerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Account> accounts;
	private Account editedAccount;
	private int currentAccountIndex;
	private Account createdAccount = new Account();

	public SecurityOfficerBean() {
		super();
		setAccounts(initializeAccounts());
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

	private List<Account> initializeAccounts() {

		Account a1 = new Account();
		a1.setLogin("Login1");
		a1.setPsw("Password1");
		a1.setSurname("Surname1");
		a1.setName("Name1");
		a1.setMiddlename("MIddlename1");
		a1.setActive(true);

		AccountRights ar11 = new AccountRights();
		ar11.setAccount(a1);
		ar11.setAccountRole(AccountRole.ADMINISTRATOR);
		ar11.setAccountRoleActive(false);

		AccountRights ar12 = new AccountRights();
		ar12.setAccount(a1);
		ar12.setAccountRole(AccountRole.ACCOUNTANT);
		ar12.setAccountRoleActive(true);

		AccountRights ar13 = new AccountRights();
		ar13.setAccount(a1);
		ar13.setAccountRole(AccountRole.ANALITYC);
		ar13.setAccountRoleActive(true);

		AccountRights ar14 = new AccountRights();
		ar14.setAccount(a1);
		ar14.setAccountRole(AccountRole.OFFICER);
		ar14.setAccountRoleActive(false);

		List<AccountRights> accr1 = new ArrayList<>();
		accr1.add(ar11);
		accr1.add(ar12);
		accr1.add(ar13);
		accr1.add(ar14);

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
		ar21.setAccountRoleActive(true);

		AccountRights ar22 = new AccountRights();
		ar22.setAccount(a2);
		ar22.setAccountRole(AccountRole.ACCOUNTANT);
		ar22.setAccountRoleActive(false);

		AccountRights ar23 = new AccountRights();
		ar23.setAccount(a2);
		ar23.setAccountRole(AccountRole.ANALITYC);
		ar23.setAccountRoleActive(false);

		AccountRights ar24 = new AccountRights();
		ar24.setAccount(a2);
		ar24.setAccountRole(AccountRole.OFFICER);
		ar24.setAccountRoleActive(false);

		List<AccountRights> accr2 = new ArrayList<>();
		accr2.add(ar21);
		accr2.add(ar22);
		accr2.add(ar23);
		accr2.add(ar24);

		a2.setAccountRights(accr2);

		List<Account> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);

		return list;

	}

}
