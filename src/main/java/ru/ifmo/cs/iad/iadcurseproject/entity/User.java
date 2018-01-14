package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String surname;
	private String patronymic; // отчество
	private Timestamp birthDate;
	private boolean sex;
	// TODO: soundcloud
	// TODO: facebook or smth
	// TODO: telegram

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
	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Basic
	@Column(name = "patronymic")
	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Basic
	@Column(name = "birth_date")
	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	@Basic
	@Column(name = "sex")
	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
}
