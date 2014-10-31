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
@Table(name = "history")
public class History implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "username")
	private User user;

	@Column(name = "action_name", length = 100, nullable = false)
	private String actionName;

	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_date")
	private Date modificationDate;

	@Column(name = "modified_object_code", length = 1000, nullable = false)
	private String modifiedObjectCode;

	@Column(name = "details", length = 1000)
	private String details;

	public History() {
	}

	public History(int id, User user, String actionName, Date modificationDate, String modifiedObjectCode,
	        String details) {
		this.id = id;
		this.user = user;
		this.actionName = actionName;
		this.modificationDate = modificationDate;
		this.modifiedObjectCode = modifiedObjectCode;
		this.details = details;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifiedObjectCode() {
		return modifiedObjectCode;
	}

	public void setModifiedObjectCode(String modifiedObjectCode) {
		this.modifiedObjectCode = modifiedObjectCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
