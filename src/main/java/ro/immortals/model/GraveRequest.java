package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

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

	public GraveRequest(Integer id, String nrInfocet, Date registrationDate,
			String solvingStage) {
		this.id = id;
		this.nrInfocet = nrInfocet;
		this.registrationDate = registrationDate;
		this.solvingStage = solvingStage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return (nrInfocet != null ? "Nr infocet:" + nrInfocet + ", " : "")
				+ (registrationDate != null ? "Data inregistrare:"
						+ registrationDate + ", " : "")
				+ (solvingStage != null ? "Stadiu solutionare:" + solvingStage
						: "");
	}

}
