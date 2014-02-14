package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.SecurityOfficerService;
import entity.Account;
import entity.AccountRights;
import entity.AccountRole;

@Named
@Scope("request")
public class SecurityOfficerBean {

	@Inject
	private SecurityOfficerService securityOfficerService;	
	private List<Account> accounts;
	private Account editedAccount;
	private int currentAccountIndex;
	private List<AccountRights> availableAccountRights;
	private Account createdAccount = new Account();

	@PostConstruct
	public void initialize() {
		setAccounts(securityOfficerService.findAllAccounts());
		
		AccountRights ar11 = new AccountRights();
		ar11.setAccountRole(AccountRole.ADMINISTRATOR);
		AccountRights ar12 = new AccountRights();
		ar12.setAccountRole(AccountRole.ACCOUNTANT);
		AccountRights ar13 = new AccountRights();
		ar13.setAccountRole(AccountRole.ANALITYC);
		
		availableAccountRights = new ArrayList<>();
		availableAccountRights.add(ar11);
		availableAccountRights.add(ar12);
		availableAccountRights.add(ar13);
	}
	
	public List<AccountRights> getAvailableAccountRights() {
		return availableAccountRights;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setAvailableAccountRights(
			List<AccountRights> availableAccountRights) {
		this.availableAccountRights = availableAccountRights;
	}

	public List<Account> getAccounts() {
		return accounts;
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

	public void save() {

	}

}
