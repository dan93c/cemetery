package ro.immortals.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claims_book")
public class ClaimBook {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "complainer", length = 100)
	private String complainer;

	@Column(name = "claims", length = 500, nullable = false)
	private String claims;

	public ClaimBook() {
	}

	public ClaimBook(Integer id, String complainer, String claims) {
		this.id = id;
		this.complainer = complainer;
		this.claims = claims;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComplainer() {
		return complainer;
	}

	public void setComplainer(String complainer) {
		if (complainer == null) {
			this.complainer = "Anonim";
		}
		this.complainer = complainer;
	}

	public String getClaims() {
		return claims;
	}

	public void setClaims(String claims) {
		this.claims = claims;
	}

}
