package ro.immortals.domain;

import ro.immortals.model.Dead;

public class DeadFrontEnd {
	private Dead dead;
	private String nrGrave;
	private String plotName;
	private String cemeteryName;
	
	public DeadFrontEnd() {
	}

	public DeadFrontEnd(Dead dead, String nrGrave, String plotName,
			String cemeteryName) {
		this.dead = dead;
		this.nrGrave = nrGrave;
		this.plotName = plotName;
		this.cemeteryName = cemeteryName;
	}

	public Dead getDead() {
		return dead;
	}

	public void setDead(Dead dead) {
		this.dead = dead;
	}

	public String getNrGrave() {
		return nrGrave;
	}

	public void setNrGrave(String nrGrave) {
		this.nrGrave = nrGrave;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getCemeteryName() {
		return cemeteryName;
	}

	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}
	
	
	
}
