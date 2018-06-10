package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.util.List;

@Getter
@Relation(value = "profile", collectionRelation = "profile")
@ToString
public class ProfileDTO extends UserExtendedDTO {
	List<NewsForFeedDTO> news;
	List<CourseInfoDTO> courses;

	public ProfileDTO() {}

	public ProfileDTO(User user, List<NewsForFeedDTO> news, List<CourseInfoDTO> courses) {
		super(user);
		this.news = news;
		this.courses = courses;
	}
}
