package dao;

import java.util.*;

import model.*;

public interface ReservationDAO extends DAO<Reservation> {
	List<Reservation> getExpiredReservations();
	List<Reservation> getActualReservations();
	
}
