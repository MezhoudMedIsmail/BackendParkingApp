package tn.esprit.spring.shared.dto;

import java.io.Serializable;
import java.util.Date;

import tn.esprit.spring.entities.Voiture;

/*
 * Data Transfer Object (DTO) représentant les informations d'un utilisateur.
 * La classe implémente Serializable pour la sérialisation.
 */
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean emailVerificationStatus;
	private String encryptedPassword;
	private String post_title;
	private long phone_num;
	private Date hiring_date;
	private Voiture voitures;
	private String role;

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Voiture getVoitures() {
		return voitures;
	}

	public void setVoitures(Voiture voitures) {
		this.voitures = voitures;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public long getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(long phone_num) {
		this.phone_num = phone_num;
	}

	public Date getHiring_date() {
		return hiring_date;
	}

	public void setHiring_date(Date hiring_date) {
		this.hiring_date = hiring_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
