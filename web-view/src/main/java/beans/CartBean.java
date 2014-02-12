package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Flight;

@Named
@Scope("session")
public class CartBean {

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
	private String surname;
	private String name;
	private String middlename;
	private String email;
	
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
	
	public void createReservation() {
		
	}
}
