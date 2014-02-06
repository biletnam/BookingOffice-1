package dao;

import java.sql.Date;
import java.util.*;

import entity.*;

public interface FlightDao1 extends GenericDao<Flight> {
	List<Flight> find(String arrival, Date departureDate);
	
}
