package beans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Flight;
import service.AdministratorService;
import service.CustomerService;

@Named
@Scope("request")
public class FlightBean {
	@Inject
	private AdministratorService administratorService;
	@Inject 
	private CustomerService customerService;
	@Inject
	private BackingBean backingBean;
	private Flight flight;
	private int amountOfOrderedTickets;
	@Inject
	private CartBean cart;
	
	@PostConstruct
	public void initialize() {
		if (backingBean.getFlightId() == 0) {
			setFlight(new Flight());
		} else {
			setFlight(administratorService
					.readFlight(backingBean.getFlightId()));
		}
	}

	public AdministratorService getAdministratorService() {
		return administratorService;
	}

	public void setAdministratorService(
			AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getAmountOfOrderedTickets() {
		return amountOfOrderedTickets;
	}

	public void setAmountOfOrderedTickets(int amountOfOrderedTickets) {
		this.amountOfOrderedTickets = amountOfOrderedTickets;
	}

	public void deleteFlight() {
		administratorService.deleteFlight(flight.getId());
	}

	public void saveFlight() {
		if (flight.getId() == 0) {
			administratorService.createFlight(flight);
		} else {
			administratorService.updateFlight(flight);
		}
	}

	public void addTicketsToCart() {
		customerService.updateFlightCart(flight, amountOfOrderedTickets);
		Map<Flight, Integer> tickets = cart.getTickets();
		tickets.put(flight, amountOfOrderedTickets);
		
		
	}
	
	public long getAmountOfSoldTickets() {
		return administratorService.countSoldTickets(flight);
	}
	
	public long getAmountOfBookedTickets() {
		return administratorService.countBookedTickets(flight);
	}
	
}
