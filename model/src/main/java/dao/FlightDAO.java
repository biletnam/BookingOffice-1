package dao;

import java.sql.Date;
import java.util.*;

import model.*;

public interface FlightDAO extends DAO<Flight> {
	List<Flight> find(String arrival, Date departureDate);
	
}
