package ru.ifmo.cs.iad.iadcurseproject.entity.custom;

import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

public abstract class Loop {
	private Long id;
	private User user;
	private Timestamp date;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
