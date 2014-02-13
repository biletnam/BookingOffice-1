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
	private int currentFlightIndex;
	private Flight editedFlight;
	private Flight createdFlight;
	private String flightNumberFilter;
	
	@PostConstruct
	public void init() {
		setFlights(administratorService.findFlights(flightNumberFilter));
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public int getCurrentFlightIndex() {
		return currentFlightIndex;
	}
	public void setCurrentFlightIndex(int currentFlightIndex) {
		this.currentFlightIndex = currentFlightIndex;
	}
	public Flight getEditedFlight() {
		return editedFlight;
	}
	public void setEditedFlight(Flight editedFlight) {
		this.editedFlight = editedFlight;
	}
	
	public Flight getCreatedFlight() {
		return createdFlight;
	}
	public void setCreatedFlight(Flight createdFlight) {
		this.createdFlight = createdFlight;
	}
	
	public String getFlightNumberFilter() {
		return flightNumberFilter;
	}

	public void setFlightNumberFilter(String flightNumberFilter) {
		this.flightNumberFilter = flightNumberFilter;
	}

	public void save() {
        flights.set(currentFlightIndex, editedFlight);
    }
	
	public void remove() {
        flights.remove(flights.get(currentFlightIndex));
    }
	
	public void create() {
        flights.add(createdFlight);
        setCreatedFlight(new Flight());
    }
	
	public void filter() {
		
	}
}
