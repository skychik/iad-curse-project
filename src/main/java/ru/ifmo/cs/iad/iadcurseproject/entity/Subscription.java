package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_who", nullable = false)
	private long whoId;
	public long getWhoId() {
		return whoId;
	}
	public void setWhoId(long whoId) {
		this.whoId = whoId;
	}

	@ManyToOne
	@JoinColumn(name = "id_on_whom", nullable = false)
	private long onWhomId;
	public long getOnWhomId() {
		return onWhomId;
	}
	public void setOnWhomId(long onWhomId) {
		this.onWhomId = onWhomId;
	}

	@Column(name = "date", nullable = false)
	private Timestamp date;
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
