package service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import dao.TicketDao;

@Named
public class AnalitycService {
	
	@Inject
	private TicketDao ticketDao;
	
	void getDataReportByDay(Date startDate, Date endDate) {
		ticketDao.getDataByDay(startDate, endDate);
	}

	void getDataReportByArrival(Date startDate, Date endDate) {
		ticketDao.getDataByArrival(startDate, endDate);
	}
}
