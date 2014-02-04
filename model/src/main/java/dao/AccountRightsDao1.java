package dao;

import java.util.*;

import model.*;

public interface AccountRightsDao1 extends GenericDao<AccountRights> {
	List<AccountRights> getAccountRights(Account account);
	
}
