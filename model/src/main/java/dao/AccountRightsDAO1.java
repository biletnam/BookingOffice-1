package dao;

import java.util.*;

import model.*;

public interface AccountRightsDAO1 extends GenericDAO<AccountRights> {
	List<AccountRights> getAccountRights(Account account);
	
}
