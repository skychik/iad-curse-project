package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "performer", schema = "public")
@ToString
public class Performer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "performer_id", nullable = false)
	private String performerId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "photo")
	private String photo;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "breakup_date")
	private Timestamp breakupDate;

	@OneToMany(mappedBy="performer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<JoiningPerformer> joiningPerformers;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPerformerId() {
		return performerId;
	}
	public void setPerformerId(String performerId) {
		this.performerId = performerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getBreakupDate() {
		return breakupDate;
	}
	public void setBreakupDate(Timestamp breakupDate) {
		this.breakupDate = breakupDate;
	}
	public Set<JoiningPerformer> getJoiningPerformers() {
		return joiningPerformers;
	}
	public void setJoiningPerformers(Set<JoiningPerformer> joiningPerformers) {
		this.joiningPerformers = joiningPerformers;
	}
}
