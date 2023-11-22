package tn.esprit.spring.responses;

//Déclaration de la classe UploadFileResponse utilisée pour encapsuler les informations de réponse après le téléchargement d'un fichier.
public class UploadFileResponse {

	// Attribut représentant le nom du fichier téléchargé.
	private String fileName;

	// Attribut représentant l'URI de téléchargement du fichier.
	private String fileDownloadUri;

	// Attribut représentant le type de fichier.
	private String fileType;

	// Attribut représentant la taille du fichier.
	private long size;

	// Constructeur de la classe qui initialise les attributs avec les valeurs
	// spécifiées.
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	// Méthode permettant d'obtenir le nom du fichier téléchargé.
	public String getFileName() {
		return fileName;
	}

	// Méthode permettant de définir le nom du fichier téléchargé.
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// Méthode permettant d'obtenir l'URI de téléchargement du fichier.
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	// Méthode permettant de définir l'URI de téléchargement du fichier.
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	// Méthode permettant d'obtenir le type de fichier.
	public String getFileType() {
		return fileType;
	}

	// Méthode permettant de définir le type de fichier.
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	// Méthode permettant d'obtenir la taille du fichier.
	public long getSize() {
		return size;
	}

	// Méthode permettant de définir la taille du fichier.
	public void setSize(long size) {
		this.size = size;
	}
}
