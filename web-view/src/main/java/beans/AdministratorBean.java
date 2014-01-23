package beans;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Flight;

@ManagedBean(name = "administratorBean", eager = true)
@SessionScoped
public class AdministratorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Flight> allFlights = null;
	private int currentFlightIndex;
	private Flight editedFlight = new Flight();
	private Flight createdFlight;
	
	
	
	public AdministratorBean() {
		setAllFlights(initializeListFlights());;
	}
	public List<Flight> getAllFlights() {
		return allFlights;
	}
	public void setAllFlights(List<Flight> allFlights) {
		this.allFlights = allFlights;
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
	private List<Flight> initializeListFlights() {

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

		ArrayList<Flight> list = new ArrayList<Flight>();
		list.add(f1);
		list.add(f2);

		return list;
	}
	
	public void store() {
        allFlights.set(currentFlightIndex, editedFlight);
    }
	
	public void remove() {
        allFlights.remove(allFlights.get(currentFlightIndex));
    }
	
	public void create() {
        allFlights.add(createdFlight);
        setCreatedFlight(new Flight());
    }
	

}
