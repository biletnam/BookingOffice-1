package beans;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
	private Date dateDepartureValidation; 
	
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
			flight.setTicketFreeAmount(flight.getTicketAmount());
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
	
	public Date getDateDepartureValidation() {
		return dateDepartureValidation;
	}

	public void setDateDepartureValidation(Date dateDepartureValidation) {
		this.dateDepartureValidation = dateDepartureValidation;
	}

	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		int ticketFreeAmount = getFlight().getTicketFreeAmount();
		int ticketOrderedAmount = (int)value;
		
		if (ticketOrderedAmount > ticketFreeAmount) {
			throw new ValidatorException(new FacesMessage());
		}
	}
	
	public void validateDateDeparture(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Date dateDeparture = (Date) value;
		setDateDepartureValidation(dateDeparture);
		Date dateDepartureStarts = Calendar.getInstance().getTime();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dateDepartureStarts);
		gc.add(Calendar.DAY_OF_YEAR, 3);
		dateDepartureStarts = gc.getTime();
		
		if (dateDeparture.before(dateDepartureStarts)) {
			throw new ValidatorException(new FacesMessage());
		}
	}
	
	public void validateDateArrival(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Date dateArrival = (Date) value;
		Date dateDeparture = getDateDepartureValidation();
		
		if (dateDeparture == null || dateDeparture.after(dateArrival)) {
			throw new ValidatorException(new FacesMessage());
		}
	}
}
