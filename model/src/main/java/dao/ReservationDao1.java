package dao;

import java.util.*;

import model.*;

public interface ReservationDao1 extends GenericDao<Reservation> {
	List<Reservation> getExpiredReservations();
	List<Reservation> getActualReservations();
	
}
