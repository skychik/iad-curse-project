package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;

import java.sql.Timestamp;

@Getter
@Relation(value = "course", collectionRelation = "course")
@ToString
public class CourseDTO {
	private Long id;
	private UserDTO author;
	private String title;
	private String type;
	private Timestamp creationDate;

	public CourseDTO (Course course) {
		id = course.getId();
		author = new UserDTO(course.getAuthor());
		title = course.getTitle();
		type = course.getType();
		creationDate = course.getCreationDate();
	}
}
