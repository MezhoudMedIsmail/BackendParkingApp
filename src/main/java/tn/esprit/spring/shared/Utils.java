package tn.esprit.spring.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
/*
 * Classe utilitaire pour la g�n�ration d'identifiants alphanum�riques
 * al�atoires.
 */
public class Utils {

	private final Random RANDOM = new SecureRandom();

	// Ensemble des caract�res autoris�s pour la g�n�ration de l'identifiant
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/*
	 * G�n�re un identifiant alphanum�rique al�atoire de la longueur sp�cifi�e.
	 */
	public String generateUserId(int length) {

		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}
}
