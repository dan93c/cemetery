package ro.immortals.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cemeteries")
public class Cemetery implements java.io.Serializable {

	@Id
	@Column(name = "code", length = 45, nullable = false)
	private String code;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "address", length = 100)
	private String address;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "cemetery")
	private List<Plot> plots = new ArrayList<Plot>();

	public Cemetery() {
	}

	public Cemetery(String code, String name, String address) {
		this.code = code;
		this.name = name;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Plot> getPlots() {
		return plots;
	}

	public void setPlots(List<Plot> plots) {
		this.plots = plots;
	}

}
