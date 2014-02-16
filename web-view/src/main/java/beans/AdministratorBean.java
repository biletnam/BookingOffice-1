package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AdministratorService;
import entity.Flight;

@Named
@Scope("request")
public class AdministratorBean {
	
	@Inject
	private AdministratorService administratorService;
	
	private List<Flight> flights;
	private String flightNumberFilter;
	
	@PostConstruct
	public void initialize() {
		setFlights(administratorService.findFlights(flightNumberFilter));
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	public String getFlightNumberFilter() {
		return flightNumberFilter;
	}

	public void setFlightNumberFilter(String flightNumberFilter) {
		this.flightNumberFilter = flightNumberFilter;
	}
	
	public void filterFlights() {
		setFlights(administratorService.findFlights(flightNumberFilter));
	}

	public long getAmountOfExpiredReservation() {
		return administratorService.countExpiredReservations();
	}
	
	public void converExpiredReservations() {
		administratorService.convertExpiredReservations();
	}

}
