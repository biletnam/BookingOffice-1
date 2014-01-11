package dao;

import java.util.*;

import model.*;

public interface AccountRightsDAO extends DAO<AccountRights> {
	List<AccountRights> getAccountRights(Account account);
	
}
