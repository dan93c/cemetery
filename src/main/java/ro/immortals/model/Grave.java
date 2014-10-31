package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "graves")
public class Grave implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nr_grave", length = 45)
	private String nrGrave;

	@Column(name = "observations", length = 45)
	private String observations;

	@Column(name = "grave_type", length = 45)
	private String graveType;

	@Column(name = "photo_scanned", columnDefinition = "LONGBLOB")
	private byte[] photoScanned;

	@ManyToOne(targetEntity = Plot.class)
	@JoinColumn(name = "plot_id")
	private Plot plot;

	@OneToMany(mappedBy = "grave")
	private List<Dead> deads = new ArrayList<Dead>();

	@OneToMany(mappedBy = "grave")
	private List<DeadWithoutFamily> deadsWithoutFamily = new ArrayList<DeadWithoutFamily>();

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "grave")
	private List<Appointment> appointments = new ArrayList<Appointment>();

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "grave")
	private List<ConsessionContract> consessionContracts = new ArrayList<ConsessionContract>();

	public Grave() {
	}

	public Grave(int id, String nrGrave, String observations, String graveType, byte[] photoScanned, Plot plot) {
		this.id = id;
		this.nrGrave = nrGrave;
		this.observations = observations;
		this.graveType = graveType;
		this.photoScanned = photoScanned;
		this.plot = plot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNrGrave() {
		return nrGrave;
	}

	public void setNrGrave(String nrGrave) {
		this.nrGrave = nrGrave;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getGraveType() {
		return graveType;
	}

	public void setGraveType(String graveType) {
		this.graveType = graveType;
	}

	public byte[] getPhotoScanned() {
		return photoScanned;
	}

	public void setPhotoScanned(byte[] photoScanned) {
		this.photoScanned = photoScanned;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public List<Dead> getDeads() {
		return deads;
	}

	public void setDeads(List<Dead> deads) {
		this.deads = deads;
	}

	public List<DeadWithoutFamily> getDeadsWithoutFamily() {
		return deadsWithoutFamily;
	}

	public void setDeadsWithoutFamily(List<DeadWithoutFamily> deadsWithoutFamily) {
		this.deadsWithoutFamily = deadsWithoutFamily;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<ConsessionContract> getConsessionContracts() {
		return consessionContracts;
	}

	public void setConsessionContracts(List<ConsessionContract> consessionContracts) {
		this.consessionContracts = consessionContracts;
	}

}