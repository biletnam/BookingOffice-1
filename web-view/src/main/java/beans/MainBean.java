package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Flight;

@Named
@Scope("request")
public class MainBean {
	private Date filterDateDeparture;
	private String filterArrival;
	private int currentFlightIndex;
	private Flight selectedFlight;
	private int amountOfOrderedTickets;
	private List<Flight> flights;
	@Inject
	private CartBean cart;

	public MainBean() {
		super();
		filterArrival = null;
		filterDateDeparture = null;
		flights = null;
		cart = new CartBean();
	}

	public CartBean getCart() {
		return cart;
	}

	public void setCart(CartBean cart) {
		this.cart = cart;
	}

	public int getCurrentFlightIndex() {
		return currentFlightIndex;
	}

	public void setCurrentFlightIndex(int currentFlightIndex) {
		this.currentFlightIndex = currentFlightIndex;
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public Date getFilterDateDeparture() {
		return filterDateDeparture;
	}

	public void setFilterDateDeparture(Date filterDateDeparture) {
		this.filterDateDeparture = filterDateDeparture;
	}

	public String getFilterArrival() {
		return filterArrival;
	}

	public void setFilterArrival(String filterArrival) {
		this.filterArrival = filterArrival;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public int getAmountOfOrderedTickets() {
		return amountOfOrderedTickets;
	}

	public void setAmountOfOrderedTickets(int amountOfOrderedTickets) {
		this.amountOfOrderedTickets = amountOfOrderedTickets;
	}

	public void find() {
		setFlights(initializeFlights());
	}

	public void addTicket() {
		Map<Flight, Integer> tickets = cart.getTickets();
		tickets.put(selectedFlight, amountOfOrderedTickets);
		amountOfOrderedTickets = 0;
	}

	public void removeTicket() {
		Map<Flight, Integer> tickets = cart.getTickets();
		tickets.remove(cart.getCurrentEntry().getKey());
	}

	private List<Flight> initializeFlights() {

		Flight f1 = new Flight();
		f1.setFlightNumber("PS-711");
		f1.setDeparture("Kyiv");
		f1.setArrival("Stambul");

		GregorianCalendar gcDeparture = new GregorianCalendar(2013,
				Calendar.DECEMBER, 24, 6, 40, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime()
				.getTime());

		GregorianCalendar gcArrival = new GregorianCalendar(2013,
				Calendar.DECEMBER, 24, 8, 40, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime()
				.getTime());

		f1.setDateDeparture(dtDeparture);
		f1.setDateArrival(dtArrival);
		f1.setTicketAmount(10);
		f1.setTicketFreeAmount(8);
		f1.setTicketPrice(1000);

		Flight f2 = new Flight();
		f2.setFlightNumber("AQ-021");
		f2.setDeparture("Kyiv");
		f2.setArrival("Roma");

		gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10,
				20, 0);
		dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());

		gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 10,
				0);
		dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());

		f2.setDateDeparture(dtDeparture);
		f2.setDateArrival(dtArrival);
		f2.setTicketAmount(5);
		f2.setTicketFreeAmount(3);
		f2.setTicketPrice(1500);

		List<Flight> list = new ArrayList<Flight>();
		list.add(f1);
		list.add(f2);

		return list;
	}

}
