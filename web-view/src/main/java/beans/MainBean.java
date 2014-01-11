package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import model.Flight;

import org.eclipse.persistence.internal.queries.ArrayListContainerPolicy;


public class MainBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date filterDepartureDate;
	private String filterArrival;
	private ArrayList<Flight> listFlight;
	
	
	

}
