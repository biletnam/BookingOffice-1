package service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dao.TicketDao;
import entity.DataForReport;

@Named
public class AnalitycService {
	
	@Inject
	private TicketDao ticketDao;
	
	@Transactional
	public List<DataForReport> getDataReportByDay(Date startDate, Date endDate) {
		return ticketDao.getDataByDay(startDate, endDate);
	}

	@Transactional
	public List<DataForReport> getDataReportByArrival(Date startDate, Date endDate) {
		return ticketDao.getDataByArrival(startDate, endDate);
	}
}
