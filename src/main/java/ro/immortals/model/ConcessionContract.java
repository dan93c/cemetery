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
@Table(name = "concession_contracts")
public class ConcessionContract implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "receipt_number", length = 45, nullable = false)
	private String receiptNr;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date")
	private Date realeaseDate;

	@Column(name = "cnp", length = 15, nullable = false)
	private String cnp;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "email_address", length = 100)
	private String emailAddress;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	public ConcessionContract() {
	}

	public ConcessionContract(Integer id, String receiptNr, Date realeaseDate, String cnp, String firstName,
	        String lastName, String address, String emailAddress, Grave grave) {
		this.id = id;
		this.receiptNr = receiptNr;
		this.realeaseDate = realeaseDate;
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailAddress = emailAddress;
		this.grave = grave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReceiptNr() {
		return receiptNr;
	}

	public void setReceiptNr(String receiptNr) {
		this.receiptNr = receiptNr;
	}

	public Date getRealeaseDate() {
		return realeaseDate;
	}

	public void setRealeaseDate(Date realeaseDate) {
		this.realeaseDate = realeaseDate;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Grave getGrave() {
		return grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

}
