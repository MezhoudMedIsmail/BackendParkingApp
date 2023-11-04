package tn.esprit.spring.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voiture implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_proprietaire;
    
    @Column(name = "matricule")
    private String matricule;
    
    @Column(name="type")
    private String type;
    
	public long getId_proprietaire() {
		return id_proprietaire;
	}
	public void setId_proprietaire(long id_proprietaire) {
		this.id_proprietaire = id_proprietaire;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
