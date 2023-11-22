package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.repositories.UserRepository;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	public List<UserEntity> getAllusers() {
		List<UserEntity> users = userService.retrieveAllUsers(); // r�cup�re tous les utilisateurs à partir du service
		List<UserEntity> sortedUsers = userService.sortUsers(users); // trie les utilisateurs
		return sortedUsers;
	}

	public void sendSimpleEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public void sendEmailWithTemplate(UserEntity user) {
		// Cr�er une instance de MimeMessage � l'aide de la fonction JavaMailSender
		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					"UTF-8");

			// D�finir l'adresse �lectronique du destinataire
			helper.setTo(user.getEmail());

			// D�finir l'objet de l'e-mail
			helper.setSubject("Alert Parking");

			// Charger le mod�le de courrier �lectronique � partir d'un fichier ou d'une
			// cha�ne de caract�res
			String emailTemplate = "<!DOCTYPE html>\r\n" + "<html lang=\"fr\">\r\n" + "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <style>\r\n" + "        body {\r\n" + "            font-family: Arial, sans-serif;\r\n"
					+ "            background-color: #f4f4f4;\r\n" + "        }\r\n" + "\r\n"
					+ "        .container {\r\n" + "            max-width: 600px;\r\n"
					+ "            margin: 0 auto;\r\n" + "            padding: 20px;\r\n"
					+ "            background-color: #ffffff;\r\n" + "            border-radius: 5px;\r\n"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "        }\r\n" + "\r\n"
					+ "        h1 {\r\n" + "            color: #333;\r\n" + "        }\r\n" + "\r\n" + "        p {\r\n"
					+ "            color: #555;\r\n" + "        }\r\n" + "\r\n" + "        .btn {\r\n"
					+ "            display: inline-block;\r\n" + "            padding: 10px 20px;\r\n"
					+ "            background-color: #007BFF;\r\n" + "            color: #fff;\r\n"
					+ "            text-decoration: none;\r\n" + "            border-radius: 3px;\r\n"
					+ "            margin-top: 10px;\r\n" + "        }\r\n" + "\r\n" + "        .btn:hover {\r\n"
					+ "            background-color: #0056b3;\r\n" + "        }\r\n" + "    </style>\r\n"
					+ "</head>\r\n" + "<body>\r\n" + "    <div class=\"container\">\r\n"
					+ "        <h1>Bonjour, {{recipientName}}</h1>\r\n"
					+ "        <p>Votre r�servation a �t� acc�pt�e.</p>\r\n"
					+ "        <p>Merci de nous informer en cas d'absence.</p>\r\n"
					+ "        <p>Votre nom d'utilisateur : {{username}}</p>\r\n"
					+ "        <p>Votre adresse e-mail : {{email}}</p>\r\n" + "    </div>\r\n" + "</body>\r\n"
					+ "</html>\r\n";

			// Remplacer les espaces r�serv�s par des valeurs r�elles
			emailTemplate = emailTemplate.replace("{{recipientName}}", user.getLastName() + " " + user.getFirstName());
			emailTemplate = emailTemplate.replace("{{username}}", user.getLastName() + " " + user.getFirstName());
			emailTemplate = emailTemplate.replace("{{email}}", user.getEmail());

			// D�finir le contenu de l'e-mail en HTML
			helper.setText(emailTemplate, true);

			// Envoyer l'email
			emailSender.send(message);
		} catch (MessagingException e) {
			// Handle messaging exceptions
		}
	}

	// Cette m�thode sera ex�cut�e tous les jours � 10:00 AM(0 0 10 * * ?) ("0 2(7)
	// 19 * * ?")
	@Scheduled(cron = "0 0 11 * * ?")
	public void myScheduledTask() {
		List<UserEntity> users = this.getAllusers();
		for (UserEntity userEntity : users) {
			this.sendEmailWithTemplate(userEntity);
		}
	}

}
