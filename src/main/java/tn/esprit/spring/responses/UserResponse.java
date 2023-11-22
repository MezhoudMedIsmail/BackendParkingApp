package tn.esprit.spring.responses;

import java.util.Date;

import tn.esprit.spring.entities.Voiture;

//D�claration de la classe UserResponse utilis�e pour encapsuler les informations en r�ponse � une requ�te utilisateur.
public class UserResponse {

	// Attribut repr�sentant l'identifiant unique de l'utilisateur.
	private String userId;

	// Attribut repr�sentant le pr�nom de l'utilisateur.
	private String firstName;

	// Attribut repr�sentant le nom de famille de l'utilisateur.
	private String lastName;

	// Attribut repr�sentant l'adresse email de l'utilisateur.
	private String email;

	// Attribut repr�sentant le titre du poste de l'utilisateur.
	private String post_title;

	// Attribut repr�sentant le num�ro de t�l�phone de l'utilisateur.
	private long phone_num;

	// Attribut repr�sentant la date d'embauche de l'utilisateur.
	private Date hiring_date;

	// Attribut repr�sentant la voiture associ�e � l'utilisateur.
	private Voiture voitures;

	// Attribut repr�sentant le r�le de l'utilisateur.
	private String role;

	// M�thode permettant d'obtenir le r�le de l'utilisateur.
	public String getRole() {
		return role;
	}

	// M�thode permettant de d�finir le r�le de l'utilisateur.
	public void setRole(String role) {
		this.role = role;
	}

	// M�thode permettant d'obtenir la voiture associ�e � l'utilisateur.
	public Voiture getVoitures() {
		return voitures;
	}

	// M�thode permettant de d�finir la voiture associ�e � l'utilisateur.
	public void setVoitures(Voiture voitures) {
		this.voitures = voitures;
	}

	// M�thode permettant d'obtenir le titre du poste de l'utilisateur.
	public String getPost_title() {
		return post_title;
	}

	// M�thode permettant de d�finir le titre du poste de l'utilisateur.
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	// M�thode permettant d'obtenir le num�ro de t�l�phone de l'utilisateur.
	public long getPhone_num() {
		return phone_num;
	}

	// M�thode permettant de d�finir le num�ro de t�l�phone de l'utilisateur.
	public void setPhone_num(long phone_num) {
		this.phone_num = phone_num;
	}

	// M�thode permettant d'obtenir la date d'embauche de l'utilisateur.
	public Date getHiring_date() {
		return hiring_date;
	}

	// M�thode permettant de d�finir la date d'embauche de l'utilisateur.
	public void setHiring_date(Date hiring_date) {
		this.hiring_date = hiring_date;
	}

	// M�thode permettant d'obtenir l'identifiant unique de l'utilisateur.
	public String getUserId() {
		return userId;
	}

	// M�thode permettant de d�finir l'identifiant unique de l'utilisateur.
	public void setUserId(String userId) {
		this.userId = userId;
	}

	// M�thode permettant d'obtenir le pr�nom de l'utilisateur.
	public String getFirstName() {
		return firstName;
	}

	// M�thode permettant de d�finir le pr�nom de l'utilisateur.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// M�thode permettant d'obtenir le nom de famille de l'utilisateur.
	public String getLastName() {
		return lastName;
	}

	// M�thode permettant de d�finir le nom de famille de l'utilisateur.
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// M�thode permettant d'obtenir l'adresse email de l'utilisateur.
	public String getEmail() {
		return email;
	}

	// M�thode permettant de d�finir l'adresse email de l'utilisateur.
	public void setEmail(String email) {
		this.email = email;
	}
}
