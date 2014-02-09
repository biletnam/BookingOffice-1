package service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.TicketDao;

@Named
public class AnalitycService {
	
	@Inject
	private TicketDao ticketDao;
	
	@Transactional
	void getDataReportByDay(Date startDate, Date endDate) {
		ticketDao.getDataByDay(startDate, endDate);
	}

	@Transactional
	void getDataReportByArrival(Date startDate, Date endDate) {
		ticketDao.getDataByArrival(startDate, endDate);
	}
}
