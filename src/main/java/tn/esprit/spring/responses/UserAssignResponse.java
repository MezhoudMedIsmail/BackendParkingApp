package tn.esprit.spring.responses;

import tn.esprit.spring.entities.PlaceParking;

//Déclaration de la classe UserAssignResponse utilisée pour encapsuler les informations en réponse à une requête d'assignation d'utilisateur à une place de parking.
public class UserAssignResponse {

	// Attribut représentant l'identifiant unique de l'utilisateur.
	private long id;

	// Attribut représentant le prénom de l'utilisateur.
	private String firstName;

	// Attribut représentant la place de parking associée à l'utilisateur.
	private PlaceParking placeparkings;

	// Méthode permettant d'obtenir l'identifiant unique de l'utilisateur.
	public long getId() {
		return id;
	}

	// Méthode permettant de définir l'identifiant unique de l'utilisateur.
	public void setId(long id) {
		this.id = id;
	}

	// Méthode permettant d'obtenir le prénom de l'utilisateur.
	public String getFirstName() {
		return firstName;
	}

	// Méthode permettant de définir le prénom de l'utilisateur.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Méthode permettant d'obtenir la place de parking associée à l'utilisateur.
	public PlaceParking getPlaceparkings() {
		return placeparkings;
	}

	// Méthode permettant de définir la place de parking associée à l'utilisateur.
	public void setPlaceparkings(PlaceParking placeparkings) {
		this.placeparkings = placeparkings;
	}
}
