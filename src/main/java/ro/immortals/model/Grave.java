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
	private Integer id;

	@Column(name = "nr_grave", length = 45)
	private String nrGrave;

	@Column(name = "observations", length = 100)
	private String observations;

	@Column(name = "type", length = 45)
	private String type;

	@Column(name = "surface", length = 45)
	private String surface;

	@Column(name = "photo_scanned", length = 200)
	private String photoScanned;

	@ManyToOne(targetEntity = Plot.class)
	@JoinColumn(name = "plot_id")
	private Plot plot;

	@OneToMany(mappedBy = "grave")
	private List<DeadWithoutFamily> deadsWithoutFamily = new ArrayList<DeadWithoutFamily>();

	@OneToMany(mappedBy = "grave")
	private List<FuneralFile> funeralFiles = new ArrayList<FuneralFile>();

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "grave")
	private List<ConcessionContract> concessionContracts = new ArrayList<ConcessionContract>();

	public Grave() {
	}

	public Grave(Integer id, String nrGrave, String observations, String graveType, String surface,
	        String photoScanned, Plot plot) {
		this.id = id;
		this.nrGrave = nrGrave;
		this.observations = observations;
		this.type = graveType;
		this.surface = surface;
		this.photoScanned = photoScanned;
		this.plot = plot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getType() {
		return type;
	}

	public void setType(String graveType) {
		this.type = graveType;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getPhotoScanned() {
		return photoScanned;
	}

	public void setPhotoScanned(String photoScanned) {
		this.photoScanned = photoScanned;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public List<DeadWithoutFamily> getDeadsWithoutFamily() {
		return deadsWithoutFamily;
	}

	public void setDeadsWithoutFamily(List<DeadWithoutFamily> deadsWithoutFamily) {
		this.deadsWithoutFamily = deadsWithoutFamily;
	}

	public List<FuneralFile> getFuneralFiles() {
		return funeralFiles;
	}

	public void setFuneralFiles(List<FuneralFile> funeralFiles) {
		this.funeralFiles = funeralFiles;
	}

	public List<ConcessionContract> getConcessionContracts() {
		return concessionContracts;
	}

	public void setConcessionContracts(List<ConcessionContract> concessionContracts) {
		this.concessionContracts = concessionContracts;
	}

}