package dao;

import java.util.*;

import entity.*;

public interface AccountRightsDAO extends DAO<AccountRights> {
	List<AccountRights> getAccountRights(Account account);
	
}
