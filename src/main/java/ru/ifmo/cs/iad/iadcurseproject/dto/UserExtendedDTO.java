package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "user_extended", collectionRelation = "user_extended")
@ToString
public class UserExtendedDTO extends UserDTO {
	private String firstName;
	private String surname;
	private String patronymic;
	private Timestamp birthDate;
	private Boolean sex;
	private boolean subscribed;

	public UserExtendedDTO() {}

	public UserExtendedDTO(User user, boolean subscribed) {
		super(user);
		this.firstName = user.getFirstName();
		this.surname = user.getSurname();
		this.patronymic = user.getPatronymic();
		this.birthDate = user.getBirthDate();
		this.sex = user.getSex();
		this.subscribed = subscribed;
	}
}
