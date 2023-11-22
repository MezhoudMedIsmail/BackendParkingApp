package tn.esprit.spring.shared;

/*
 * Exception personnalisée pour la gestion des erreurs liées au stockage de fichiers.
 */
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/*
	 * Constructeur avec un message d'erreur.
	 */
	public FileStorageException(String message) {
		super(message);
	}

	/*
	 * Constructeur avec un message d'erreur et une cause sous-jacente.
	 */
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
