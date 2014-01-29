package beans;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Flight;

@ManagedBean(name = "cartBean", eager = true)
@SessionScoped
public class CartBean {
	private String customerSurname;
	private String customerName;
	private String customerMiddlename;
	private double sum;
	private int amount;
	private Map<Flight, Integer> tickets = new HashMap<Flight,Integer>();
	
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMiddlename() {
		return customerMiddlename;
	}
	public void setCustomerMiddlename(String customerMiddlename) {
		this.customerMiddlename = customerMiddlename;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Map<Flight, Integer> getTickets() {
		return tickets;
	}
	public void setTickets(Map<Flight, Integer> tickets) {
		this.tickets = tickets;
	}
	
}
