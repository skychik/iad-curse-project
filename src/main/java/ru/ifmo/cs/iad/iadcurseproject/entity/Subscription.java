package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "subscription", schema = "public")
public class Subscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_who", nullable = false)
	@JsonManagedReference
	private User who;

	@ManyToOne
	@JoinColumn(name = "id_on_whom", nullable = false)
	@JsonManagedReference
	private User onWhom;

	@Column(name = "date", nullable = false)
	private Timestamp date;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getWho() {
		return who;
	}
	public void setWho(User who) {
		this.who = who;
	}
	public User getOnWhom() {
		return onWhom;
	}
	public void setOnWhom(User onWhom) {
		this.onWhom = onWhom;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
