package tn.esprit.spring.responses;

//D�claration de la classe UploadFileResponse utilis�e pour encapsuler les informations de r�ponse apr�s le t�l�chargement d'un fichier.
public class UploadFileResponse {

	// Attribut repr�sentant le nom du fichier t�l�charg�.
	private String fileName;

	// Attribut repr�sentant l'URI de t�l�chargement du fichier.
	private String fileDownloadUri;

	// Attribut repr�sentant le type de fichier.
	private String fileType;

	// Attribut repr�sentant la taille du fichier.
	private long size;

	// Constructeur de la classe qui initialise les attributs avec les valeurs
	// sp�cifi�es.
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	// M�thode permettant d'obtenir le nom du fichier t�l�charg�.
	public String getFileName() {
		return fileName;
	}

	// M�thode permettant de d�finir le nom du fichier t�l�charg�.
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// M�thode permettant d'obtenir l'URI de t�l�chargement du fichier.
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	// M�thode permettant de d�finir l'URI de t�l�chargement du fichier.
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	// M�thode permettant d'obtenir le type de fichier.
	public String getFileType() {
		return fileType;
	}

	// M�thode permettant de d�finir le type de fichier.
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	// M�thode permettant d'obtenir la taille du fichier.
	public long getSize() {
		return size;
	}

	// M�thode permettant de d�finir la taille du fichier.
	public void setSize(long size) {
		this.size = size;
	}
}
