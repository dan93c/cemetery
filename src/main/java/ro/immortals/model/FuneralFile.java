package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "funeral_files")
public class FuneralFile implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "funeral_date")
	private Date funeralDate;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "funeralFile", cascade = CascadeType.ALL)
	private Dead dead;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	public FuneralFile() {
	}

	public FuneralFile(Integer id, Date funeralDate, Dead dead, Grave grave) {
		this.id = id;
		this.funeralDate = funeralDate;
		this.dead = dead;
		this.grave = grave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
