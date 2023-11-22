package tn.esprit.spring.requests;

import java.util.Date;

import tn.esprit.spring.entities.Voiture;

//Déclaration de la classe UserRequest utilisée pour encapsuler les informations lors de la création d'un utilisateur.
public class UserRequest {

	// Attribut représentant le prénom de l'utilisateur.
	private String firstName;

	// Attribut représentant le nom de famille de l'utilisateur.
	private String lastName;

	// Attribut représentant l'adresse email de l'utilisateur.
	private String email;

	// Attribut représentant le mot de passe de l'utilisateur.
	private String password;

	// Attribut représentant le titre du poste de l'utilisateur.
	private String post_title;

	// Attribut représentant le numéro de téléphone de l'utilisateur.
	private long phone_num;

	// Attribut représentant la date d'embauche de l'utilisateur.
	private Date hiring_date;

	// Attribut représentant la voiture associée à l'utilisateur.
	private Voiture voitures;

	// Attribut représentant le rôle de l'utilisateur.
	private String role;

	// Méthode permettant d'obtenir le rôle de l'utilisateur.
	public String getRole() {
		return role;
	}

	// Méthode permettant de définir le rôle de l'utilisateur.
	public void setRole(String role) {
		this.role = role;
	}

	// Méthode permettant d'obtenir la voiture associée à l'utilisateur.
	public Voiture getVoitures() {
		return voitures;
	}

	// Méthode permettant de définir la voiture associée à l'utilisateur.
	public void setVoitures(Voiture voitures) {
		this.voitures = voitures;
	}

	// Méthode permettant d'obtenir le titre du poste de l'utilisateur.
	public String getPost_title() {
		return post_title;
	}

	// Méthode permettant de définir le titre du poste de l'utilisateur.
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	// Méthode permettant d'obtenir le numéro de téléphone de l'utilisateur.
	public long getPhone_num() {
		return phone_num;
	}

	// Méthode permettant de définir le numéro de téléphone de l'utilisateur.
	public void setPhone_num(long phone_num) {
		this.phone_num = phone_num;
	}

	// Méthode permettant d'obtenir la date d'embauche de l'utilisateur.
	public Date getHiring_date() {
		return hiring_date;
	}

	// Méthode permettant de définir la date d'embauche de l'utilisateur.
	public void setHiring_date(Date hiring_date) {
		this.hiring_date = hiring_date;
	}

	// Méthode permettant d'obtenir le prénom de l'utilisateur.
	public String getFirstName() {
		return firstName;
	}

	// Méthode permettant de définir le prénom de l'utilisateur.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Méthode permettant d'obtenir le nom de famille de l'utilisateur.
	public String getLastName() {
		return lastName;
	}

	// Méthode permettant de définir le nom de famille de l'utilisateur.
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Méthode permettant d'obtenir l'adresse email de l'utilisateur.
	public String getEmail() {
		return email;
	}

	// Méthode permettant de définir l'adresse email de l'utilisateur.
	public void setEmail(String email) {
		this.email = email;
	}

	// Méthode permettant d'obtenir le mot de passe de l'utilisateur.
	public String getPassword() {
		return password;
	}

	// Méthode permettant de définir le mot de passe de l'utilisateur.
	public void setPassword(String password) {
		this.password = password;
	}
}
