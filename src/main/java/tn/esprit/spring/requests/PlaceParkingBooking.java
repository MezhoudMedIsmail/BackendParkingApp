package tn.esprit.spring.requests;

import java.sql.Date;
import java.time.LocalDateTime;

//Déclaration de la classe PlaceParkingBooking qui représente la réservation ou la planification d'une place de parking.
public class PlaceParkingBooking {

	// Attribut représentant la date de début de la réservation.
	private Date startDate;

	// Attribut représentant la date de fin de la réservation.
	private Date endDate;

	// Attribut représentant le statut de la réservation (activée ou désactivée).
	private boolean status;

	// Méthode permettant d'obtenir la date de début de la réservation.
	public Date getStartDate() {
		return startDate;
	}

	// Méthode permettant de définir la date de début de la réservation.
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// Méthode permettant d'obtenir la date de fin de la réservation.
	public Date getEndDate() {
		return endDate;
	}

	// Méthode permettant de définir la date de fin de la réservation.
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// Méthode permettant d'obtenir le statut de la réservation.
	public boolean getStatus() {
		return status;
	}

	// Méthode permettant de définir le statut de la réservation.
	public void setStatus(boolean status) {
		this.status = status;
	}
}
