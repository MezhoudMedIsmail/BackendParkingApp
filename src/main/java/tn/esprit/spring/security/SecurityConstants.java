package tn.esprit.spring.security;

//D�claration de la classe SecurityConstants qui contient des constantes li�es � la s�curit�.
public class SecurityConstants {

	// Dur�e d'expiration d'un jeton JWT en millisecondes (10 jours).
	public static final long EXPIRATION_TIME = 864000000;

	// Pr�fixe � utiliser pour les jetons JWT dans l'en-t�te d'authentification.
	public static final String TOKEN_PREFIX = "Bearer ";

	// Nom de l'en-t�te d'authentification o� le jeton JWT est inclus.
	public static final String HEADER_STRING = "Authorization";

	// URL � utiliser pour l'inscription des utilisateurs (sign-up).
	public static final String SIGN_UP_URL = "/users/**";

	// Cl� secr�te utilis�e pour signer les jetons JWT.
	public static final String TOKEN_SECRET = "dfg893hdc475zwerop4tghg4ddfdfgdsdfeqaas?=-0ljznm0-9";
}
