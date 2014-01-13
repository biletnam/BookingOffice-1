package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.*;

import model.Flight;

@ManagedBean(name = "main", eager = true)
@SessionScoped
public class MainBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date filterDateDeparture;
	private String filterArrival;
	private ArrayList<Flight> listFlights;
	
	public MainBean() {
		super();
		setListFlights(initializeListFlights());
		
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

	public ArrayList<Flight> getListFlights() {
		return listFlights;
	}

	public void setListFlights(ArrayList<Flight> listFlights) {
		this.listFlights = listFlights;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private ArrayList<Flight> initializeListFlights() {
		
		Flight f1 = new Flight();
		f1.setFlightNumber("PS-711");
		f1.setDeparture("Kyiv");
		f1.setArrival("Stambul");
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 24, 6, 40, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 24, 8, 40, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		f1.setDateDeparture(dtDeparture);
		f1.setDateArrival(dtArrival);
		f1.setTicketAmount(10);
		f1.setTicketAmount(1000);
		
		Flight f2 = new Flight();
		f2.setFlightNumber("AQ-021");
		f2.setDeparture("Kyiv");
		f2.setArrival("Roma");
		
		gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 20, 0);
		dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 10, 0);
		dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		f2.setDateDeparture(dtDeparture);
		f2.setDateArrival(dtArrival);
		f2.setTicketAmount(5);
		f2.setTicketAmount(1500);
		
		ArrayList<Flight> list = new ArrayList<Flight>();
		list.add(f1);
		list.add(f2);
		
		return list;
	}
	
}
