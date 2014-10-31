package ro.immortals.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "consession_contracts")
public class ConsessionContract implements java.io.Serializable{

	@Id
	@Column(name = "current_nr", length = 45, nullable = false)
	private String currentNr;

	@Column(name = "receipt_nr", length = 45, nullable = false)
	private String receiptNr;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date")
	private Date realeaseDate;

	@Column(name = "cnp", length = 15, nullable = false)
	private String cnp;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "address", length = 100)
	private String address;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	public ConsessionContract() {
	}

	public ConsessionContract(String currentNr, String receiptNr, Date realeaseDate, String cnp, String name,
	        String surname, String address, Grave grave) {
		super();
		this.currentNr = currentNr;
		this.receiptNr = receiptNr;
		this.realeaseDate = realeaseDate;
		this.cnp = cnp;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.grave = grave;
	}

	public String getCurrentNr() {
		return currentNr;
	}

	public void setCurrentNr(String currentNr) {
		this.currentNr = currentNr;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Grave getGrave() {
		return grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

}
