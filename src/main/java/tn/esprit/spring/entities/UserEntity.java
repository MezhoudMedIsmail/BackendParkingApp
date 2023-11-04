package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6830902149883544893L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String userId;
	
	@Column(nullable = false , length=50)
	private String firstName;
	
	@Column( nullable = false , length=50)
	private String lastName;
	
	@Column(nullable = false , length=50 , unique=true)
    private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	
	@Column(nullable = false ,length=50)
	private String post_title;
	
	@Column(nullable = false , length=50)
	private long phone_num;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING , pattern="yyyy-MM-dd")
	private Date hiring_date;
	
	@Column(nullable = false , length=50)
	private String role;
	
	@OneToOne(cascade = { CascadeType.ALL})
	private Voiture voitures;
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
    private PlaceParking placeParking;

	

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public PlaceParking getPlaceParking() {
		return placeParking;
	}

	public void setPlaceParking(PlaceParking placeParking) {
		this.placeParking = placeParking;
	}
}