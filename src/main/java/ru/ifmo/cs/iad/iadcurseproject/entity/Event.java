package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class Event implements Serializable {
	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;


	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getAlteringDate() {
		return alteringDate;
	}
	public void setAlteringDate(Timestamp alteringDate) {
		this.alteringDate = alteringDate;
	}
}
