package tn.esprit.spring.requests;

import java.sql.Date;
import java.time.LocalDateTime;

public class PlaceParkingBooking {
	private Date startDate;
	private Date endDate;
	private boolean status;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
