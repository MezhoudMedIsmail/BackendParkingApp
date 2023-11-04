package tn.esprit.spring.responses;

import tn.esprit.spring.entities.PlaceParking;

public class UserAssignResponse {
	private long id;
    private String firstName;
	private PlaceParking placeparkings;
	public long getId() {
		return id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	public PlaceParking getPlaceparkings() {
		return placeparkings;
	}
	public void setPlaceparkings(PlaceParking placeparkings) {
		this.placeparkings = placeparkings;
	}
}
