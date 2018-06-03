package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "course", collectionRelation = "course")
@ToString
public class CoursePostedDTO {
	private String type;
	private String title;

	public CoursePostedDTO() {}

	public Course makeCourse(User author) {
		Course course = new Course();
		course.setAuthor(author);
		course.setTitle(title);
		course.setType(type);
		course.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return course;
	}
}
