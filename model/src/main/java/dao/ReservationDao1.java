package dao;

import java.util.*;

import entity.*;

public interface ReservationDao1 extends GenericDao<Reservation> {
	List<Reservation> getExpiredReservations();
	List<Reservation> getActualReservations();
	
}
