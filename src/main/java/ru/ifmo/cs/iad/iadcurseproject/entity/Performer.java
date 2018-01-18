package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "performer")
public class Performer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic
	@Column(name = "id", nullable = false)
	private Long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
