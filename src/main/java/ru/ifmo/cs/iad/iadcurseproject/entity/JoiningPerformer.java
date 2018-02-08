package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "joining_performer")
public class JoiningPerformer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_performer", nullable = false)
	private long performerId;
	public long getPerformerId() {
		return performerId;
	}
	public void setPerformerId(long performerId) {
		this.performerId = performerId;
	}

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "joining_date", nullable = false)
	private Timestamp joiningDate;
	public Timestamp getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Column(name = "exit_date", nullable = false)
	private Timestamp exitDate;
	public Timestamp getExitDate() {
		return exitDate;
	}
	public void setExitDate(Timestamp exitDate) {
		this.exitDate = exitDate;
	}
}
