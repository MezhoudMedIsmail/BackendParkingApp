package tn.esprit.spring.requests;

import java.sql.Date;
import java.time.LocalDateTime;

//D�claration de la classe PlaceParkingBooking qui repr�sente la r�servation ou la planification d'une place de parking.
public class PlaceParkingBooking {

	// Attribut repr�sentant la date de d�but de la r�servation.
	private Date startDate;

	// Attribut repr�sentant la date de fin de la r�servation.
	private Date endDate;

	// Attribut repr�sentant le statut de la r�servation (activ�e ou d�sactiv�e).
	private boolean status;

	// M�thode permettant d'obtenir la date de d�but de la r�servation.
	public Date getStartDate() {
		return startDate;
	}

	// M�thode permettant de d�finir la date de d�but de la r�servation.
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// M�thode permettant d'obtenir la date de fin de la r�servation.
	public Date getEndDate() {
		return endDate;
	}

	// M�thode permettant de d�finir la date de fin de la r�servation.
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// M�thode permettant d'obtenir le statut de la r�servation.
	public boolean getStatus() {
		return status;
	}

	// M�thode permettant de d�finir le statut de la r�servation.
	public void setStatus(boolean status) {
		this.status = status;
	}
}
