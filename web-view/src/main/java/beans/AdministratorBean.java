package beans;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import entity.Flight;

@Named
@ManagedBean(name = "administratorBean", eager = true)
@Scope("request")
public class AdministratorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Flight> flights = new ArrayList<>();
	private int currentFlightIndex;
	private Flight editedFlight;
	private Flight createdFlight = new Flight();
	private String flightNumberFilter;
	
	public AdministratorBean() {
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
		f2.setTicketPrice(1500);

		flights = new ArrayList<Flight>();
		flights.add(f1);
		flights.add(f2);
		
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
