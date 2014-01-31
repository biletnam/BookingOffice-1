package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private List<Map.Entry<Flight, Integer>> entries;
	private int currentEntryIndex;
	private Map.Entry<Flight, Integer> currentEntry;
	private int newAmountOfOrderedTickets;
	
	public int getNewAmountOfOrderedTickets() {
		return newAmountOfOrderedTickets;
	}

	public void setNewAmountOfOrderedTickets(int newAmountOfOrderedTickets) {
		this.newAmountOfOrderedTickets = newAmountOfOrderedTickets;
	}

	public int getCurrentEntryIndex() {
		return currentEntryIndex;
	}

	public void setCurrentEntryIndex(int currentEntryIndex) {
		this.currentEntryIndex = currentEntryIndex;
	}

	public Map.Entry<Flight, Integer> getCurrentEntry() {
		return currentEntry;
	}

	public void setCurrentEntry(Map.Entry<Flight, Integer> currentEntry) {
		this.currentEntry = currentEntry;
	}

	public List<Map.Entry<Flight, Integer>> getEntries() {
		entries = new ArrayList<Map.Entry<Flight, Integer>>(tickets.entrySet());
		return entries;
	}

	public void setEntries(List<Map.Entry<Flight, Integer>> entries) {
		this.entries = entries;
	}

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

	public void updateCurrentEntry() {
		tickets.put(currentEntry.getKey(), newAmountOfOrderedTickets);
	}
}
