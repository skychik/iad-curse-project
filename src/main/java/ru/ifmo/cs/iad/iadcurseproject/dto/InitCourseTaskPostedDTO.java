package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTask;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "course_task", collectionRelation = "course_task")
@ToString
public class InitCourseTaskPostedDTO {
	private String title;
	private String content;
	private CoursePostedDTO course;

	public InitCourseTaskPostedDTO() {}

	public CourseTask makeTask(User author) {
		CourseTask task = new CourseTask();
		task.getCourse().setAuthor(author);
		task.setCourse(course.makeCourse(author));
		task.setTaskTitle(title);
		task.setContent(content);
		task.setContentPreview(content);
		task.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return task;
	}
}
