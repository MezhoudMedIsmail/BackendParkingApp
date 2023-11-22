package tn.esprit.spring.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
/*
 * Classe utilitaire pour la génération d'identifiants alphanumériques
 * aléatoires.
 */
public class Utils {

	private final Random RANDOM = new SecureRandom();

	// Ensemble des caractères autorisés pour la génération de l'identifiant
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/*
	 * Génère un identifiant alphanumérique aléatoire de la longueur spécifiée.
	 */
	public String generateUserId(int length) {

		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}
}
