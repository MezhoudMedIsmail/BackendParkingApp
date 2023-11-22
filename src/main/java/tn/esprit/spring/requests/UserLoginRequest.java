package tn.esprit.spring.requests;

//Déclaration de la classe UserLoginRequest utilisée pour encapsuler les informations de connexion d'un utilisateur.
public class UserLoginRequest {

	// Attribut représentant l'adresse email de l'utilisateur.
	private String email;

	// Attribut représentant le mot de passe de l'utilisateur.
	private String password;

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
