package beans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.SecurityOfficerService;
import entity.Account;

@Named
@Scope("request")
public class SecurityOfficerBean {

	@Inject
	private SecurityOfficerService securityOfficerService;	
	private List<Account> accounts;
	
	@PostConstruct
	public void initialize() {
		setAccounts(securityOfficerService.findAllAccounts());
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	
}
