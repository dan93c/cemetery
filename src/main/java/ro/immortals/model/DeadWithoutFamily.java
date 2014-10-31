package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deads_without_family")
public class DeadWithoutFamily implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "funeral_certificate", length = 50)
	private String feneralCertificate;
	
	@Column(name = "iml_request", length = 50)
	private String imlRequest;

	@ManyToOne(targetEntity = Grave.class)
	@JoinColumn(name = "grave_id")
	private Grave grave;

	public DeadWithoutFamily() {
    }

	public DeadWithoutFamily(int id, String feneralCertificate, String imlRequest, Grave grave) {
	    this.id = id;
	    this.feneralCertificate = feneralCertificate;
	    this.imlRequest = imlRequest;
	    this.grave = grave;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeneralCertificate() {
		return feneralCertificate;
	}

	public void setFeneralCertificate(String feneralCertificate) {
		this.feneralCertificate = feneralCertificate;
	}

	public String getImlRequest() {
		return imlRequest;
	}

	public void setImlRequest(String imlRequest) {
		this.imlRequest = imlRequest;
	}

	public Grave getGrave() {
		return grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

}
