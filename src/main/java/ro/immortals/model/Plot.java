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
@Table(name = "plots")
public class Plot implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surface", length = 45)
	private String surface;

	@ManyToOne(targetEntity = Cemetery.class)
	@JoinColumn(name = "cemetery_id")
	private Cemetery cemetery;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "plot")
	private List<Grave> graves = new ArrayList<Grave>();

	public Plot() {
	}

	public Plot(int id, String name, String surface, Cemetery cemetery) {
		this.id = id;
		this.name = name;
		this.surface = surface;
		this.cemetery = cemetery;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public Cemetery getCemetery() {
		return cemetery;
	}

	public void setCemetery(Cemetery cemetery) {
		this.cemetery = cemetery;
	}

	public List<Grave> getGraves() {
		return graves;
	}

	public void setGraves(List<Grave> graves) {
		this.graves = graves;
	}

}
