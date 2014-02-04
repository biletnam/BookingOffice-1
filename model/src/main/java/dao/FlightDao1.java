package dao;

import java.sql.Date;
import java.util.*;

import model.*;

public interface FlightDao1 extends GenericDao<Flight> {
	List<Flight> find(String arrival, Date departureDate);
	
}
