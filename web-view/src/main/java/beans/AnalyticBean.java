package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AnalitycService;
import entity.DataForReport;

@Named
@ManagedBean(name = "analyticBean", eager = true)
@Scope("request")
public class AnalyticBean implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public List<DataForReport> getReport1() {
		return reportByDay;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void viewReport() {

		setReportByDay(analitycService.getDataReportByDay(dateStart, dateEnd));
		setReportByArrival(analitycService.getDataReportByArrival(dateStart,
				dateEnd));
	}

}
