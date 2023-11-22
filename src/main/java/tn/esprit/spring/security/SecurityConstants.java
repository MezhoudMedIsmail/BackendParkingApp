package tn.esprit.spring.security;

//Déclaration de la classe SecurityConstants qui contient des constantes liées à la sécurité.
public class SecurityConstants {

	// Durée d'expiration d'un jeton JWT en millisecondes (10 jours).
	public static final long EXPIRATION_TIME = 864000000;

	// Préfixe à utiliser pour les jetons JWT dans l'en-tête d'authentification.
	public static final String TOKEN_PREFIX = "Bearer ";

	// Nom de l'en-tête d'authentification où le jeton JWT est inclus.
	public static final String HEADER_STRING = "Authorization";

	// URL à utiliser pour l'inscription des utilisateurs (sign-up).
	public static final String SIGN_UP_URL = "/users/**";

	// Clé secrète utilisée pour signer les jetons JWT.
	public static final String TOKEN_SECRET = "dfg893hdc475zwerop4tghg4ddfdfgdsdfeqaas?=-0ljznm0-9";
}
