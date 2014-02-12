package entity;

public enum AccountRole {
	ADMINISTRATOR("Administrator"), ACCOUNTANT("Accountant"), ANALITYC("Analytic");
	private String name;
	
	private AccountRole(String name) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
