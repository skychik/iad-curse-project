package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@RepositoryRestController
@RequestMapping("/course")
public class CourseController {
	private final UserRepo userRepo;
	private final CourseRepo courseRepo;
	private final CourseTaskRepo courseTaskRepo;
	private final CourseTaskLoopRepo courseTaskLoopRepo;
	private final CourseTaskPoopRepo courseTaskPoopRepo;
	private final TaskCommentRepo taskCommentRepo;
	private final TaskCommentLoopRepo taskCommentLoopRepo;
	private final TaskCommentPoopRepo taskCommentPoopRepo;
	private final CourseTaskCompletionRepo courseTaskCompletionRepo;
	private final CourseSubscriptionRepo courseSubscriptionRepo;

	private final String[] COURSE_TYPES = {"dj", "drums", "flute", "guitar", "vocal"};

	private Logger logger = LoggerFactory.getLogger("CourseController");

	public CourseController(UserRepo userRepo, CourseRepo courseRepo, CourseTaskRepo courseTaskRepo,
	                        TaskCommentRepo taskCommentRepo, TaskCommentLoopRepo taskCommentLoopRepo,
	                        TaskCommentPoopRepo taskCommentPoopRepo, CourseTaskLoopRepo courseTaskLoopRepo,
	                        CourseTaskPoopRepo courseTaskPoopRepo, CourseTaskCompletionRepo courseTaskCompletionRepo,
	                        CourseSubscriptionRepo courseSubscriptionRepo) {
		this.userRepo = userRepo;
		this.courseRepo = courseRepo;
		this.courseTaskRepo = courseTaskRepo;
		this.courseTaskLoopRepo = courseTaskLoopRepo;
		this.courseTaskPoopRepo = courseTaskPoopRepo;
		this.taskCommentRepo = taskCommentRepo;
		this.taskCommentLoopRepo = taskCommentLoopRepo;
		this.taskCommentPoopRepo = taskCommentPoopRepo;
		this.courseTaskCompletionRepo = courseTaskCompletionRepo;
		this.courseSubscriptionRepo = courseSubscriptionRepo;
	}


	@GetMapping("/tasks")
	@ResponseBody
	public ResponseEntity getTasksForUserId(@CookieValue(value = "userId") long userId,
	                                        @RequestParam(value = "type", required = false, defaultValue = "all") String type,
	                                        @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                        @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("tasks of userId=" + userId);
		if (!userRepo.findById(userId).isPresent()) {
			return logAndGetBadRequestEntity("userId=" + userId + " doesn't exist");
		}

		// TODO: incorrect order for course tasks
		List<CourseTask> taskList = courseTaskRepo.getAllForUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.asc("creationDate"))));
		Collections.reverse(taskList);

		if (!type.equals("all")) {
			taskList = taskList.stream().filter((task) -> task.getCourse().getType().equals(type)).collect(Collectors.toList());
		}

		logger.info(taskList.toString());

		return new ResponseEntity<>(taskList.stream()
				.map((CourseTask task) -> new CourseTaskPreviewDTO(task, (long) task.getTaskComments().size(),
						(long) task.getTaskLoops().size(), courseTaskLoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
						(long) task.getTaskPoops().size(), courseTaskPoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
						courseTaskCompletionRepo.getByTaskIdAndUserId(task.getId(), userId) != null))
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("list")
	@ResponseBody
	public ResponseEntity getCoursesForUserId(@CookieValue(value = "userId") long userId,
	                                          @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                          @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("courses of userId=" + userId);
		List<Course> courseList = courseRepo.getAllByAuthorId(userId, of(pageNumber, pageSize,
				by(Sort.Order.asc("creationDate"))));

		return new ResponseEntity<>(courseList.stream().map((Course course) -> new CourseInfoDTO(course,
				(long) course.getTasks().stream().mapToInt((CourseTask t) -> t.getTaskLoops().size()).sum(),
				(long) course.getTasks().stream().mapToInt((CourseTask t) -> t.getTaskPoops().size()).sum(),
				(course.getTasks().stream().mapToInt((CourseTask t) ->
						t.getTaskCompletions().size()).sum() + 0.0) / (course.getTasks().size()) + 0.0,
				courseSubscriptionRepo.getByCourseIdAndUserId(course.getId(), userId) != null))
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}")
	@ResponseBody
	public ResponseEntity getTaskById(@PathVariable("taskId") long taskId,
	                                  @CookieValue(value = "userId") long userId) {
		logger.info("getTaskById=" + taskId);
		CourseTask task = courseTaskRepo.getOneByTaskId(taskId);
		if (task == null) {
			return logAndGetBadRequestEntity("No task with id=" + taskId);
		}
		return new ResponseEntity<>(new CourseTaskDTO(task, (long) task.getTaskComments().size(),
				(long) task.getTaskLoops().size(), courseTaskLoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				(long) task.getTaskPoops().size(), courseTaskPoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				courseTaskCompletionRepo.getByTaskIdAndUserId(task.getId(), userId) != null), HttpStatus.OK);
	}

	@GetMapping("/types")
	@ResponseBody
	public String[] getCoursesTypes() {
		logger.info("getCoursesTypes");
		return COURSE_TYPES;
	}

	@GetMapping("/task/{taskId}/comments_number")
	@ResponseBody
	public ResponseEntity getCommentsNumberByTaskId(@PathVariable("taskId") long taskId) {
		logger.info("getCommentsNumberByTaskId=" + taskId);
		if (!courseTaskRepo.findById(taskId).isPresent()) {
			return logAndGetBadRequestEntity("No task with id=" + taskId);
		}
		return new ResponseEntity<>(taskCommentRepo.countAllByTaskId(taskId), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/loops_number")
	@ResponseBody
	public ResponseEntity getLoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		logger.info("getLoopsNumberByTaskId=" + taskId);
		if (!courseTaskRepo.findById(taskId).isPresent()) {
			return logAndGetBadRequestEntity("No task with id=" + taskId);
		}
		return new ResponseEntity<>(courseTaskLoopRepo.countAllByTaskId(taskId), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/poops_number")
	@ResponseBody
	public ResponseEntity getPoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		logger.info("getPoopsNumberByTaskId=" + taskId);
		if (!courseTaskRepo.findById(taskId).isPresent()) {
			return logAndGetBadRequestEntity("No task with id=" + taskId);
		}
		return new ResponseEntity<>(courseTaskPoopRepo.countAllByTaskId(taskId), HttpStatus.OK);
	}

	@GetMapping("/title_exists")
	@ResponseBody
	public Boolean doesTitleExist(@CookieValue("userId") long userId,
	                              @RequestParam("title") String title) {
		logger.info("doesTitleExist=" + title + " for userId=" + userId);
		return courseRepo.findByAuthorIdAndTitle(userId, title) != null;
	}

	@PostMapping(path="/task/init_course_with_task", consumes = "application/json", produces = "application/json")
	public ResponseEntity addNewCourseWithTask(@CookieValue("userId") long userId,
	                                                   @RequestBody InitCourseTaskPostedDTO taskDTO) {
		logger.info("add new course with task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			return logAndGetBadRequestEntity("User with id=" + userId + " doesn't exist");
		}
		Course foundCourse = courseRepo.findByAuthorIdAndTitle(userId, taskDTO.getCourse().getTitle());
		if (foundCourse != null) {
			return logAndGetBadRequestEntity("User with id=" + userId + " already has course with title=" +
					taskDTO.getTitle());
		}

		if (!isCourseTypeCorrect(taskDTO.getCourse().getType())) {
			return logAndGetBadRequestEntity("Course type=\"" + taskDTO.getCourse().getType() + "\" is incorrect");
		}
		Course course = taskDTO.getCourse().makeCourse(user.get());
		Course savedCourse = courseRepo.saveAndFlush(course);
		logger.info(savedCourse.toString());

		CourseTask task = taskDTO.makeTask(user.get(), savedCourse);
		CourseTask savedTask = courseTaskRepo.saveAndFlush(task);
		logger.info("task saved=" + savedTask.toString());
		return new ResponseEntity<>(savedTask.getId().toString(), HttpStatus.OK);
	}

	private boolean isCourseTypeCorrect(String courseType) {
		for (String type : COURSE_TYPES) {
			if (type.equals(courseType)) {
				return true;
			}
		}
		return false;
	}

	@PostMapping(path="/task/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity addTask(@CookieValue("userId") long userId,
	                              @RequestBody CourseTaskPostedDTO taskDTO) {
		logger.info("add task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		Optional<Course> course = courseRepo.findById(taskDTO.getCourseId());
		if (!user.isPresent()) {
			return logAndGetBadRequestEntity("User with id=" + userId + " doesn't exist");
		}
		if (!course.isPresent()) {
			return logAndGetBadRequestEntity("Course with id=" + taskDTO.getCourseId() + " doesn't exist");
		}
		if (course.get().getAuthor() != user.get()) {
			return logAndGetBadRequestEntity("Course with id=" + taskDTO.getCourseId() + " doesn't own user with id=" + userId);
		}
		CourseTask task = taskDTO.makeTask(course.get());
		CourseTask savedTask = courseTaskRepo.saveAndFlush(task);
		logger.info("task saved=" + savedTask.toString());
		return new ResponseEntity<>(savedTask.getId().toString(), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/loop/put")
	@ResponseBody
	public ResponseEntity putLoop(@PathVariable(value = "taskId") long taskId,
	                              @CookieValue(value = "userId") long userId) {
		logger.info("put loop taskId=" + taskId + "by userId=" + userId);
		if (courseTaskLoopRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return logAndGetBadRequestEntityWithIdValue("taskId=" + taskId + " already looped",
					taskId, courseTaskLoopRepo.countAllByTaskId(taskId));
		}
		CourseTaskLoop loop = new CourseTaskLoop();
		loop.setTask(courseTaskRepo.getOne(taskId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskLoopRepo.save(loop);
		return new ResponseEntity<>(new IdValueDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId)), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/poop/put")
	@ResponseBody
	public ResponseEntity putPoop(@PathVariable(value = "taskId") long taskId,
	                              @CookieValue(value = "userId") long userId) {
		logger.info("put poop taskId=" + taskId + "by userId=" + userId);
		if (courseTaskPoopRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return logAndGetBadRequestEntityWithIdValue("taskId=" + taskId + " already pooped",
					taskId, courseTaskPoopRepo.countAllByTaskId(taskId));
		}
		CourseTaskPoop poop = new CourseTaskPoop();
		poop.setTask(courseTaskRepo.getOne(taskId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskPoopRepo.save(poop);
		return new ResponseEntity<>(new IdValueDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId)), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/loop/remove")
	@ResponseBody
	public ResponseEntity removeLoop(@PathVariable(value = "taskId") long taskId,
	                                 @CookieValue(value = "userId") long userId) {
		logger.info("remove loop newsId=" + taskId + "by userId=" + userId);
		CourseTaskLoop loop = courseTaskLoopRepo.getByTaskIdAndUserId(taskId, userId);
		if (loop == null) {
			return logAndGetBadRequestEntityWithIdValue("taskId=" + taskId + " wasn't looped",
					taskId, courseTaskLoopRepo.countAllByTaskId(taskId));
		}
		logger.info(loop.toString());
		courseTaskLoopRepo.removeById(loop.getId());
		logger.info("deleted");
		return new ResponseEntity<>(new IdValueDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId)), HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/poop/remove")
	@ResponseBody
	public ResponseEntity removePoop(@PathVariable(value = "taskId") long taskId,
	                                 @CookieValue(value = "userId") long userId) {
		logger.info("remove poop newsId=" + taskId + "by userId=" + userId);
		CourseTaskPoop poop = courseTaskPoopRepo.getByTaskIdAndUserId(taskId, userId);
		if (poop == null) {
			return logAndGetBadRequestEntityWithIdValue("taskId=" + taskId + " wasn't pooped",
					taskId, courseTaskPoopRepo.countAllByTaskId(taskId));
		}
		logger.info(poop.toString());
		courseTaskPoopRepo.removeById(poop.getId());
		logger.info("deleted");
		return new ResponseEntity<>(new IdValueDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId)), HttpStatus.OK);
	}

	@GetMapping("/{courseId}/subscribe")
	@ResponseBody
	public ResponseEntity subscribeCourse(@PathVariable(value = "courseId") long courseId,
	                                      @CookieValue(value = "userId") long userId) {
		logger.info("subscribeCourseId=" + courseId + "by userId=" + userId);
		if (!courseRepo.findById(courseId).isPresent()) {
			return logAndGetBadRequestEntity("No course with id=" + courseId);
		}
		if (courseRepo.findById(courseId).get().getAuthor().getId() == userId) {
			return logAndGetBadRequestEntity("Can't subscribe on your course");
		}
		if (courseSubscriptionRepo.getByCourseIdAndUserId(courseId, userId) != null) {
			return logAndGetBadRequestEntity("Already subscribed on courseId=" + courseId);
		}
		CourseSubscription subscription = new CourseSubscription();
		subscription.setCourse(courseRepo.getOne(courseId));
		subscription.setUser(userRepo.getOne(userId));
		subscription.setDate(new Timestamp(System.currentTimeMillis()));
		CourseSubscription subscriptionSaved = courseSubscriptionRepo.saveAndFlush(subscription);
		logger.info("course subscription id={}", subscriptionSaved.getId());
		return new ResponseEntity<>(courseId, HttpStatus.OK);
	}

	@GetMapping("/{courseId}/unsubscribe")
	@ResponseBody
	public ResponseEntity unsubscribeCourse(@PathVariable(value = "courseId") long courseId,
	                                        @CookieValue(value = "userId") long userId) {
		logger.info("unsubscribeCourseId=" + courseId + "by userId=" + userId);
		CourseSubscription subscription = courseSubscriptionRepo.getByCourseIdAndUserId(courseId, userId);
		if (subscription == null) {
			return logAndGetBadRequestEntity("Already unsubscribed courseId=" + courseId);
		}
		logger.info(subscription.toString());
		courseSubscriptionRepo.removeById(subscription.getId());
		logger.info("deleted");
		return new ResponseEntity<>(courseId, HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/complete")
	@ResponseBody
	public ResponseEntity complete(@PathVariable(value = "taskId") long taskId,
	                      @CookieValue(value = "userId") long userId) {
		logger.info("complete taskId=" + taskId + "by userId=" + userId);
		if (courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return logAndGetBadRequestEntityWithId("taskId=" + taskId + " already completed", taskId);
		}
		CourseTaskCompletion completion = new CourseTaskCompletion();
		completion.setTask(courseTaskRepo.getOne(taskId));
		completion.setUser(userRepo.getOne(userId));
		completion.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskCompletionRepo.save(completion);
		return new ResponseEntity<>(taskId, HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}/undo")
	@ResponseBody
	public ResponseEntity undo(@PathVariable(value = "taskId") long taskId,
	                  @CookieValue(value = "userId") long userId) {
		logger.info("undo taskId=" + taskId + " by userId=" + userId);
		CourseTaskCompletion completion = courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId);
		if (completion == null) {
			return logAndGetBadRequestEntityWithId("taskId=" + taskId + " wasn't completed", taskId);
		}
		logger.info(completion.toString());
		courseTaskCompletionRepo.removeById(completion.getId());
		logger.info("deleted");
		return new ResponseEntity<>(taskId, HttpStatus.OK);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private ResponseEntity logAndGetBadRequestEntity(String msg) {
		logger.info(msg);
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity logAndGetBadRequestEntityWithId(String msg, Long id) {
		logger.info(msg);
		return new ResponseEntity<>(new IdMessageDTO(id, msg),
				HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity logAndGetBadRequestEntityWithIdValue(String msg, Long id, Long value) {
		logger.info(msg);
		return new ResponseEntity<>(new IdValueMessageDTO(id, value, msg),
				HttpStatus.BAD_REQUEST);
	}
}
