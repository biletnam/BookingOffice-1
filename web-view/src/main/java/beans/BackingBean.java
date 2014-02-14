package beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@SuppressWarnings("serial")
@Named
@Scope("session")
public class BackingBean implements Serializable {
	private int reservationId;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
}
