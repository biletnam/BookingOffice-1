package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.DataForReport;

@ManagedBean(name = "analyticBean", eager = true)
@SessionScoped
public class AnalyticBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date dateStart;
	private Date dateEnd;
	private String reportType;
	private List<DataForReport> report1;
	private List<DataForReport> report2;

	public AnalyticBean() {
		super();
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
		return report1;
	}

	public void setReport1(List<DataForReport> report1) {
		this.report1 = report1;
	}

	public List<DataForReport> getReport2() {
		return report2;
	}

	public void setReport2(List<DataForReport> report2) {
		this.report2 = report2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void viewReport() {
		setReport1(initializeReport1());
		setReport2(initializeReport2());
	}

	private List<DataForReport> initializeReport1() {
		List<DataForReport> list = new ArrayList<>();

		DataForReport dfr1 = new DataForReport();

		GregorianCalendar gcPayment = new GregorianCalendar(2013,
				Calendar.DECEMBER, 4);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime()
				.getTime());

		dfr1.setDatePayment(dtPayment);
		dfr1.setTicketCount(104);
		dfr1.setTicketPrice(1000);
		dfr1.setTicketSum(104000);
		list.add(dfr1);

		DataForReport dfr2 = new DataForReport();

		gcPayment = new GregorianCalendar(2013, Calendar.DECEMBER, 12);
		dtPayment = new java.sql.Timestamp(gcPayment.getTime().getTime());

		dfr2.setDatePayment(dtPayment);
		dfr2.setTicketCount(50);
		dfr2.setTicketPrice(100);
		dfr2.setTicketSum(5000);
		list.add(dfr2);

		return list;
	}

	private List<DataForReport> initializeReport2() {
		ArrayList<DataForReport> list = new ArrayList<>();

		DataForReport dfr1 = new DataForReport();

		dfr1.setArrival("Roma");
		dfr1.setTicketCount(200);
		dfr1.setTicketPrice(40);
		dfr1.setTicketSum(8000);
		list.add(dfr1);

		DataForReport dfr2 = new DataForReport();

		dfr2.setArrival("London");
		dfr2.setTicketCount(10);
		dfr2.setTicketPrice(2000);
		dfr2.setTicketSum(20000);
		list.add(dfr2);

		return list;
	}

}
