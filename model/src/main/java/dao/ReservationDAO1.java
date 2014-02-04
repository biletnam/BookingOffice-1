package dao;

import java.util.*;

import model.*;

public interface ReservationDAO1 extends GenericDAO<Reservation> {
	List<Reservation> getExpiredReservations();
	List<Reservation> getActualReservations();
	
}
