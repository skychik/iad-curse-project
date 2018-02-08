package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "performer")
public class Performer implements Serializable {
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

	@Column(name = "name", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status", nullable = false)
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "breakup_date")
	private Timestamp breakupDate;
	public Timestamp getBreakupDate() {
		return breakupDate;
	}
	public void setBreakupDate(Timestamp breakupDate) {
		this.breakupDate = breakupDate;
	}

	@OneToMany(mappedBy="id_performer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<JoiningPerformer> joiningPerformers;
	public Set<JoiningPerformer> getJoiningPerformers() {
		return joiningPerformers;
	}
	public void setJoiningPerformers(Set<JoiningPerformer> joiningPerformers) {
		this.joiningPerformers = joiningPerformers;
	}
}
