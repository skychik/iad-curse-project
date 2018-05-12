package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import javax.validation.constraints.Email;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.commons.validator.EmailValidator;

@Getter
@Setter
@Relation(value = "user_registration", collectionRelation = "user_registration")
@ToString
public class UserRegistrationDTO {
	private String login;
	private String password;
	private String firstName;
	private String surname;
	private String patronymic;
	private Timestamp birthDate;
	private String sex;
	private String email;

	public UserRegistrationDTO() {}

//	public UserRegistrationDTO(User user) {
//		login = user.getLogin();
//		password = user.getPassword();
//		this.firstName = user.getFirstName();
//		this.surname = user.getSurname();
//		this.patronymic = user.getPatronymic();
//		this.birthDate = user.getBirthDate();
//		if (user.getSex() == null) {
//			this.sex = "helicopter";
//		} else {
//			if (user.getSex()) {
//				this.sex = "male";
//			} else {
//				this.sex = "female";
//			}
//		}
//	}

	public User makeUser() throws IllegalArgumentException { // TODO: make validation on backend
		User user = new User();
		user.setUsername(login);
		user.setPassword(password);
		// EmailValidator validator = EmailValidator.getInstance();
		user.setFirstName(firstName);
		user.setSurname(surname);
		user.setPatronymic(patronymic);
		user.setBirthDate(birthDate);
		switch (sex) {
			case "male":
				user.setSex(true);
				break;
			case "female":
				user.setSex(false);
				break;
			default:
				// user.sex = null
				break;
		}
		return user;
	}
}
