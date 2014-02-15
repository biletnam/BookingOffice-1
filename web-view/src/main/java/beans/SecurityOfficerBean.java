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
	private static List<AccountRights> availableAccountRights;
	

	@PostConstruct
	public void initialize() {
		setAccounts(securityOfficerService.findAllAccounts());
		
		availableAccountRights = new ArrayList<>();
		AccountRights ar = new AccountRights();
		ar.setAccountRole(AccountRole.ADMINISTRATOR);
		availableAccountRights.add(ar);
		ar = new AccountRights();
		availableAccountRights.add(ar);
		ar.setAccountRole(AccountRole.ACCOUNTANT);
		ar = new AccountRights();
		ar.setAccountRole(AccountRole.ANALITYC);
		availableAccountRights.add(ar);
	}
	
	public List<AccountRights> getAvailableAccountRights() {
		return availableAccountRights;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

}
