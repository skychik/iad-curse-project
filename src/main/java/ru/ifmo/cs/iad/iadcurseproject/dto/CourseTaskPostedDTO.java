package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTask;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "course_task", collectionRelation = "course_task")
@ToString
public class CourseTaskPostedDTO {
	private long courseId;
	private String title;
	private String content;

	public CourseTaskPostedDTO() {}

	public CourseTask makeTask(Course course) {
		CourseTask task = new CourseTask();
		task.setCourse(course);
		task.setTaskTitle(title);
		task.setContent(content);
		task.setContentPreview(content);
		task.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return task;
	}
}
