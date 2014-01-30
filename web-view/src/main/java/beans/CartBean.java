package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Flight;

@ManagedBean(name = "cartBean", eager = true)
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String customerSurname;
	private String customerName;
	private String customerMiddlename;
	private double ticketsSum;
	private int ticketsAmount;
	private Map<Flight, Integer> tickets = new HashMap<Flight, Integer>();

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

	public double getTicketsSum() {

		double s = 0;
		for (Map.Entry<Flight, Integer> entry : tickets.entrySet()) {
			double el = entry.getKey().getTicketPrice() * entry.getValue();
			s = s + el;
		}

		setTicketsSum(s);
		return ticketsSum;
	}

	public void setTicketsSum(double ticketsSum) {
		this.ticketsSum = ticketsSum;
	}

	public int getTicketsAmount() {

		int a = 0;
		for (Integer i : tickets.values()) {
			a += i;
		}
		setTicketsAmount(a);
		return ticketsAmount;
	}

	public void setTicketsAmount(int ticketsAmount) {
		this.ticketsAmount = ticketsAmount;
	}

	public Map<Flight, Integer> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Flight, Integer> tickets) {
		this.tickets = tickets;
	}

}
