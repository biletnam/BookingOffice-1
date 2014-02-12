package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AnalitycService;
import entity.DataForReport;

@Named
@Scope("request")
public class AnalyticBean {

	@Inject
	private AnalitycService analitycService;

	private Date dateStart;
	private Date dateEnd;
	private String reportType;
	private List<DataForReport> reportByDay = new ArrayList<>();
	private List<DataForReport> reportByArrival = new ArrayList<>();

	public AnalyticBean() {
		reportType = "byDay";
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<DataForReport> getReportByDay() {
		return reportByDay;
	}

	public void setReportByDay(List<DataForReport> reportByDay) {
		this.reportByDay = reportByDay;
	}

	public List<DataForReport> getReportByArrival() {
		return reportByArrival;
	}

	public void setReportByArrival(List<DataForReport> reportByArrival) {
		this.reportByArrival = reportByArrival;
	}

	public void viewReport() {
		List<DataForReport> reportData = analitycService.getDataReportByDay(dateStart, dateEnd);
		setReportByDay( reportData);
		reportData = analitycService.getDataReportByArrival(dateStart, dateEnd);
		setReportByArrival(reportData);
	}
}
