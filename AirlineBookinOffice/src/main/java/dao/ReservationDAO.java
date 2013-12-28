package dao;

import java.util.*;

import entity.*;

public interface ReservationDAO extends DAO<Reservation> {
	List<Reservation> getExpiredReservations();
	List<Reservation> getActualReservations();
	
}
