package service;

import java.util.Date;

import dao.TicketDao;

public class AnalitycService {
	private TicketDao ticketDao;
	
	void getDataReportByDay(Date startDate, Date endDate) {
		ticketDao.getDataByDay(startDate, endDate);
	}

	void getDataReportByArrival(Date startDate, Date endDate) {
		ticketDao.getDataByArrival(startDate, endDate);
	}
}
