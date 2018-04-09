package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;

import java.sql.Timestamp;

@Getter
@Relation(value = "user_extended", collectionRelation = "user_extended")
@ToString
public class UserExtendedDTO {
	private Long id;
	private String username;
	private String firstName;
	private String surname;
	private String patronymic;
	private Timestamp birthDate;
	private boolean sex;

	public UserExtendedDTO(){}

	public UserExtendedDTO(Long id, String username, String firstName, String surname, String patronymic, Timestamp birthDate, boolean sex) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthDate = birthDate;
		this.sex = sex;
	}
}
