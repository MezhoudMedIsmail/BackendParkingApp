package tn.esprit.spring.responses;

import tn.esprit.spring.entities.PlaceParking;

//D�claration de la classe UserAssignResponse utilis�e pour encapsuler les informations en r�ponse � une requ�te d'assignation d'utilisateur � une place de parking.
public class UserAssignResponse {

	// Attribut repr�sentant l'identifiant unique de l'utilisateur.
	private long id;

	// Attribut repr�sentant le pr�nom de l'utilisateur.
	private String firstName;

	// Attribut repr�sentant la place de parking associ�e � l'utilisateur.
	private PlaceParking placeparkings;

	// M�thode permettant d'obtenir l'identifiant unique de l'utilisateur.
	public long getId() {
		return id;
	}

	// M�thode permettant de d�finir l'identifiant unique de l'utilisateur.
	public void setId(long id) {
		this.id = id;
	}

	// M�thode permettant d'obtenir le pr�nom de l'utilisateur.
	public String getFirstName() {
		return firstName;
	}

	// M�thode permettant de d�finir le pr�nom de l'utilisateur.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// M�thode permettant d'obtenir la place de parking associ�e � l'utilisateur.
	public PlaceParking getPlaceparkings() {
		return placeparkings;
	}

	// M�thode permettant de d�finir la place de parking associ�e � l'utilisateur.
	public void setPlaceparkings(PlaceParking placeparkings) {
		this.placeparkings = placeparkings;
	}
}
