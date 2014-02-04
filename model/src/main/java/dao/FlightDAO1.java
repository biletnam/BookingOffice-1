package dao;

import java.sql.Date;
import java.util.*;

import model.*;

public interface FlightDAO1 extends GenericDAO<Flight> {
	List<Flight> find(String arrival, Date departureDate);
	
}
