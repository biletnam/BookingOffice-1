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
public class MainBean {
	@Inject
	private AdministratorService administratorService;
	
	private int currentFlightIndex;
	private Flight selectedFlight;
	private List<Flight> flights;
	@Inject
	private BackingBean backingBean;

	@PostConstruct
	public void initialize() {
		setFlights(administratorService.findFlightsByDateDepartureAndArrival(backingBean.getFilterDateDeparture(), backingBean.getFilterArrival()));
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

	public void findFlights() {
		setFlights(administratorService.findFlightsByDateDepartureAndArrival(backingBean.getFilterDateDeparture(), backingBean.getFilterArrival()));
	}

}
