package beans;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import service.AccountantService;
import entity.Reservation;

@Named
@Scope("request")
public class ReservationBean {
	@Inject
	private AccountantService accountantService;
	@Inject
	private BackingBean backingBean;
	private Reservation reservation;

	@PostConstruct
	public void initialize() {
		setReservation(accountantService.readReservation(backingBean.getReservationId()));
	}

	public void saveReservation() {
		accountantService.updateReservation(reservation);
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public AccountantService getAccountantService() {
		return accountantService;
	}

	public void setAccountantService(AccountantService accountantService) {
		this.accountantService = accountantService;
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		Date datePayment = (Date)value;
		Date dateReservation = reservation.getDateReservation();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dateReservation);
		gc.add(Calendar.DAY_OF_YEAR, 3);
		Date datePaymentEnds = gc.getTime();
		
		
		if (datePayment.before(dateReservation) || datePayment.after(datePaymentEnds)) {
			throw new ValidatorException(new FacesMessage());
		}
	}
}
