package beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AdministratorService;
import entity.Flight;

@Named
@Scope("request")
public class MainBean {
	@Inject
	private AdministratorService administratorService;
	
	private int currentFlightIndex;
	private Flight selectedFlight;
	private int amountOfOrderedTickets;
	private List<Flight> flights;
	@Inject
	private CartBean cart;

	@PostConstruct
	public void initialize() {
		setFlights(administratorService.findFlightsAll());
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

	public void findFlights() {

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


}
