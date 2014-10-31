package ro.immortals.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "deads")
public class Dead implements java.io.Serializable{

	@Id
	@Column(name = "cnp", length = 15, nullable = false)
	private String cnp;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "religion", length = 45)
	private String religion;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	@OneToMany(mappedBy = "dead")
	private List<Appointment> appointments = new ArrayList<Appointment>();

	public Dead() {
	}

	public Dead(String cnp, String name, String surname, String religion, Grave grave) {
		this.cnp = cnp;
		this.name = name;
		this.surname = surname;
		this.religion = religion;
		this.grave = grave;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Grave getGrave() {
		return grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

}
