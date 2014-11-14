package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "appointments")
public class Appointment implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "funeral_date")
	private Date funeralDate;

	@ManyToOne(targetEntity = Dead.class)
	@JoinColumn(name = "dead_id")
	private Dead dead;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	public Appointment() {
	}

	public Appointment(int id, Date funeralDate, Dead dead, Grave grave) {
		this.id = id;
		this.funeralDate = funeralDate;
		this.dead = dead;
		this.grave = grave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFuneralDate() {
		return funeralDate;
	}

	public void setFuneralDate(Date funeralDate) {
		this.funeralDate = funeralDate;
	}

	public Dead getDead() {
		return dead;
	}

	public void setDead(Dead dead) {
		this.dead = dead;
	}

	public Grave getGrave() {
		return grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

}
