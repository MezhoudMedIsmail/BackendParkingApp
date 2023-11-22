package tn.esprit.spring.requests;

//D�claration de la classe UserLoginRequest utilis�e pour encapsuler les informations de connexion d'un utilisateur.
public class UserLoginRequest {

	// Attribut repr�sentant l'adresse email de l'utilisateur.
	private String email;

	// Attribut repr�sentant le mot de passe de l'utilisateur.
	private String password;

	// M�thode permettant d'obtenir l'adresse email de l'utilisateur.
	public String getEmail() {
		return email;
	}

	// M�thode permettant de d�finir l'adresse email de l'utilisateur.
	public void setEmail(String email) {
		this.email = email;
	}

	// M�thode permettant d'obtenir le mot de passe de l'utilisateur.
	public String getPassword() {
		return password;
	}

	// M�thode permettant de d�finir le mot de passe de l'utilisateur.
	public void setPassword(String password) {
		this.password = password;
	}
}
