package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.CustomerService;
import entity.Flight;

@SuppressWarnings("serial")
@Named
@Scope("session")
public class CartBean implements Serializable {
	@Inject
	private CustomerService customerService;
	
	private String customerSurname;
	private String customerName;
	private String customerMiddlename;
	private String customerEmail;
	private double ticketsSum;
	private Map<Flight, Integer> tickets = new HashMap<Flight, Integer>();
	private List<Map.Entry<Flight, Integer>> entries;
	private Map.Entry<Flight, Integer> currentEntry;
	private int currentEntryIndex;
	private int newAmountOfOrderedTickets;

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getNewAmountOfOrderedTickets() {
		return newAmountOfOrderedTickets;
	}

	public void setNewAmountOfOrderedTickets(int newAmountOfOrderedTickets) {
		this.newAmountOfOrderedTickets = newAmountOfOrderedTickets;
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

		int ticketsAmount = 0;
		for (Integer i : tickets.values()) {
			ticketsAmount += i;
		}
		return ticketsAmount;
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
	
	public void createReservation() {
		customerService.createReservation(entries, customerSurname, customerName, customerMiddlename, customerEmail, getTicketsSum());
	}

	public String cancelReservation() {
		clearBean();
		return "main";
	}
	
	private void clearBean() {
		tickets.clear();
		setCustomerSurname(null);
		setCustomerName(null);
		setCustomerMiddlename(null);
		setCustomerEmail(null);
	}
	
	public void deleteTicket() {
		tickets.remove(getCurrentEntry().getKey());
	}

	public int getCurrentEntryIndex() {
		return currentEntryIndex;
	}

	public void setCurrentEntryIndex(int currentEntryIndex) {
		this.currentEntryIndex = currentEntryIndex;
	}
	
	
}
