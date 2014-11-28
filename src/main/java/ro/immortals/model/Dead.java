package ro.immortals.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "deads")
public class Dead implements java.io.Serializable {

	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false)
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "funeralFile"))
	private Integer id;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "religion", length = 45)
	private String religion;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.DATE)
	@Column(name = "death_date")
	private Date deathDate;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private FuneralFile funeralFile;

	public Dead() {
	}

	public Dead(Integer id, String firstName, String lastName, String religion, Date deathDate, Grave grave) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.religion = religion;
		this.deathDate = deathDate;
		this.grave = grave;
	}

	public Dead(Integer id, String firstName, String lastName, String religion, Date deathDate, Grave grave,
	        FuneralFile funeralFile) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.religion = religion;
		this.deathDate = deathDate;
		this.grave = grave;
		this.funeralFile = funeralFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public FuneralFile getFuneralFile() {
		return funeralFile;
	}

	public void setFuneralFile(FuneralFile funeralFile) {
		this.funeralFile = funeralFile;
	}

}
