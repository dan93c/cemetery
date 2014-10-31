package ro.immortals.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "grave_requests")
public class GraveRequest implements java.io.Serializable {

	@Id
	@Column(name = "current_nr", length = 45, nullable = false)
	private String currentNr;

	@Column(name = "nr_infocet", length = 100)
	private String nrInfocet;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	@Column(name = "solving_stage", length = 45)
	private String solvingStage;

	public GraveRequest() {
	}

	public GraveRequest(String currentNr, String nrInfocet, Date registrationDate, String solvingStage) {
		this.currentNr = currentNr;
		this.nrInfocet = nrInfocet;
		this.registrationDate = registrationDate;
		this.solvingStage = solvingStage;
	}

	public String getCurrentNr() {
		return currentNr;
	}

	public void setCurrentNr(String currentNr) {
		this.currentNr = currentNr;
	}

	public String getNrInfocet() {
		return nrInfocet;
	}

	public void setNrInfocet(String nrInfocet) {
		this.nrInfocet = nrInfocet;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getSolvingStage() {
		return solvingStage;
	}

	public void setSolvingStage(String solvingStage) {
		this.solvingStage = solvingStage;
	}
}
