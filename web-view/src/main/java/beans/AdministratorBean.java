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
	@Inject
	private BackingBean backingBean;
	private List<Flight> flights;
	
	
	@PostConstruct
	public void initialize() {
		setFlights(administratorService.findFlightsByNumber(backingBean.getFlightNumberFilter()));
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	public void filterFlights() {
		
	}

	public long getAmountOfExpiredReservation() {
		return administratorService.countExpiredReservations();
	}
	
	public void converExpiredReservations() {
		administratorService.convertExpiredReservations();
	}

}
