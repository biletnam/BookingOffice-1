package beans;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Account;
import service.SecurityOfficerService;

@Named
@Scope("request")
public class AccountBean {
	@Inject
	private SecurityOfficerService securityOfficerService;
	@Inject
	private BackingBean backingBean;
	private Account account;

	@PostConstruct
	public void initialize() {
		if (backingBean.getAccountId() == 0){
			setAccount(new Account());
		} else {
			setAccount(securityOfficerService.readAccount(backingBean.getAccountId()));
		}
		
	}
	
	public void saveAccount() {
		if (account.getId() == 0) {
			securityOfficerService.createAccount(account);
		} else {
			securityOfficerService.updateAccount(account);
		}
	}
	
	public void deleteAccount() {
		securityOfficerService.deleteAccount(backingBean.getAccountId());
	}
	
	public SecurityOfficerService getSecurityOfficerService() {
		return securityOfficerService;
	}

	public void setSecurityOfficerService(
			SecurityOfficerService securityOfficerService) {
		this.securityOfficerService = securityOfficerService;
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
