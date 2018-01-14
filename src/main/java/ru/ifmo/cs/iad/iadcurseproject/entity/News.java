package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
public class News implements Serializable {
	private Long id;
	private long authorId;
	private long commentOn;
	private Timestamp creationDate;
	private Timestamp alteringDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "id_user", nullable = false)
	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	@Basic
	@Column(name = "creation_date", nullable = false)
	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
