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

	public UserDTO(){}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
	}
}
