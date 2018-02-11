package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "joining_performer", schema = "public")
public class JoiningPerformer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_performer", nullable = false)
	private Performer performer;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@Column(name = "joining_date", nullable = false)
	private Timestamp joiningDate;

	@Column(name = "exit_date", nullable = false)
	private Timestamp exitDate;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Performer getPerformer() {
		return performer;
	}
	public void setPerformer(Performer performer) {
		this.performer = performer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Timestamp getExitDate() {
		return exitDate;
	}
	public void setExitDate(Timestamp exitDate) {
		this.exitDate = exitDate;
	}
}
