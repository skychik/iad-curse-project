package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

@Getter
@Relation(value = "user", collectionRelation = "user")
@ToString
public class UserDTO {
	private Long id;
	private String username;
	private String photo = "default.png";

	public UserDTO() {}

	public UserDTO(User user) {
		id = user.getId();
		username = user.getUsername();
		if (user.getPhoto() != null) photo = user.getPhoto();
	}
}
