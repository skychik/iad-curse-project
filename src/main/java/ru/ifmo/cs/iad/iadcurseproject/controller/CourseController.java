package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import java.sql.Timestamp;
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

	private Logger logger = LoggerFactory.getLogger("application");

	public CourseController(UserRepo userRepo, CourseRepo courseRepo, CourseTaskRepo courseTaskRepo, TaskCommentRepo taskCommentRepo, TaskCommentLoopRepo taskCommentLoopRepo,
	                        TaskCommentPoopRepo taskCommentPoopRepo, CourseTaskLoopRepo courseTaskLoopRepo,
	                        CourseTaskPoopRepo courseTaskPoopRepo, CourseTaskCompletionRepo courseTaskCompletionRepo) {
		this.userRepo = userRepo;
		this.courseRepo = courseRepo;
		this.courseTaskRepo = courseTaskRepo;
		this.courseTaskLoopRepo = courseTaskLoopRepo;
		this.courseTaskPoopRepo = courseTaskPoopRepo;
		this.taskCommentRepo = taskCommentRepo;
		this.taskCommentLoopRepo = taskCommentLoopRepo;
		this.taskCommentPoopRepo = taskCommentPoopRepo;
		this.courseTaskCompletionRepo = courseTaskCompletionRepo;
	}


	@GetMapping("/tasks/for")
	@ResponseBody
	public List<CourseTaskPreviewDTO> getTasksForUserId(@CookieValue(value = "userId") long userId,
	                                             @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                             @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("" + userId);
		List<CourseTask> taskList = courseTaskRepo.getAllForUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));

		return taskList.stream()
				.map((CourseTask task) -> new CourseTaskPreviewDTO(task, (long) task.getTaskComments().size(),
						(long) task.getTaskLoops().size(), courseTaskLoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
						(long) task.getTaskPoops().size(), courseTaskPoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
						courseTaskCompletionRepo.getByTaskIdAndUserId(task.getId(), userId) != null))
				.collect(Collectors.toList());
	}

	@GetMapping("/task/{taskId}")
	@ResponseBody
	public CourseTaskDTO getTaskById(@PathVariable("taskId") long taskId,
	                          @CookieValue(value = "userId") long userId) {
		CourseTask task = courseTaskRepo.getOneByTaskId(taskId);
		if (task == null) return new CourseTaskDTO();
		return new CourseTaskDTO(task, (long) task.getTaskComments().size(),
				(long) task.getTaskLoops().size(), courseTaskLoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				(long) task.getTaskPoops().size(), courseTaskPoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				courseTaskCompletionRepo.getByTaskIdAndUserId(task.getId(), userId) != null);
	}

	@GetMapping("/task/{taskId}/comments_number")
	@ResponseBody
	public long getCommentsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return taskCommentRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/task/{taskId}/loops_number")
	@ResponseBody
	public long getLoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return courseTaskLoopRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/task/{taskId}/poops_number")
	@ResponseBody
	public long getPoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return courseTaskPoopRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/title_exists")
	@ResponseBody
	public Boolean doesTitleExist(@CookieValue("userId") long userId,
	                                            @RequestParam("title") String title) {
		return courseRepo.findByAuthorIdAndTitle(userId, title) != null;
	}

	@PostMapping(path="/task/init_course_with_task", consumes = "application/json", produces = "application/json")
	public String addNewCourseWithTask(@CookieValue("userId") long userId,
	                                   @RequestBody InitCourseTaskPostedDTO taskDTO) {
		logger.info("add new course with task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			logger.info("User with id=" + userId + " doesn't exist");
			return null;
		}
		Course course = courseRepo.findByAuthorIdAndTitle(userId, taskDTO.getCourse().getTitle());
		if (course != null) {
			logger.info("User with id=" + userId + " already has course with title " + taskDTO.getTitle());
			return null;
		}
		CourseTask task = taskDTO.makeTask(user.get());
		CourseTask savedTask = courseTaskRepo.save(task);
		logger.info("task saved=" + savedTask.toString());
		return savedTask.getId().toString();
	}

	@PostMapping(path="/task/add", consumes = "application/json", produces = "application/json")
	public String addTask(@CookieValue("userId") long userId,
	                      @RequestBody CourseTaskPostedDTO taskDTO) {
		logger.info("add task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		Optional<Course> course = courseRepo.findById(taskDTO.getCourseId());
		if (!user.isPresent()) {
			logger.info("User with id=" + userId + " doesn't exist");
			return null;
		}
		if (!course.isPresent()) {
			logger.info("Course with id=" + taskDTO.getCourseId() + " doesn't exist");
			return null;
		}
		if (course.get().getAuthor() != user.get()) {
			logger.info("Course with id=" + taskDTO.getCourseId() + " doesn't own user with id=" + userId);
			return null;
		}
		CourseTask task = taskDTO.makeTask(course.get());
		CourseTask savedTask = courseTaskRepo.save(task);
		logger.info("task saved=" + savedTask.toString());
		return savedTask.getId().toString();
	}

	@GetMapping("/task/{taskId}/loop/put")
	@ResponseBody
	public IdValueSucceedDTO putLoop(@PathVariable(value = "taskId") long taskId,
	                          @CookieValue(value = "userId") long userId) {
		logger.info("put loop taskId=" + taskId + "by userId=" + userId);
		if (courseTaskLoopRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return new IdValueSucceedDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId), false);
		}
		CourseTaskLoop loop = new CourseTaskLoop();
		loop.setTask(courseTaskRepo.getOne(taskId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskLoopRepo.save(loop);
		return new IdValueSucceedDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId), true);
	}

	@GetMapping("/task/{taskId}/poop/put")
	@ResponseBody
	public IdValueSucceedDTO putPoop(@PathVariable(value = "taskId") long taskId,
	                          @CookieValue(value = "userId") long userId) {
		logger.info("put poop taskId=" + taskId + "by userId=" + userId);
		if (courseTaskPoopRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return new IdValueSucceedDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId), false);
		}
		CourseTaskPoop poop = new CourseTaskPoop();
		poop.setTask(courseTaskRepo.getOne(taskId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskPoopRepo.save(poop);
		return new IdValueSucceedDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId), true);
	}

	@GetMapping("/task/{taskId}/loop/remove")
	@ResponseBody
	public IdValueSucceedDTO removeLoop(@PathVariable(value = "taskId") long taskId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove loop newsId=" + taskId + "by userId=" + userId);
		CourseTaskLoop loop = courseTaskLoopRepo.getByTaskIdAndUserId(taskId, userId);
		if (loop == null) {
			return new IdValueSucceedDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId), false);
		}
		System.out.println(loop.toString());
		courseTaskLoopRepo.removeById(loop.getId());
		System.out.println("deleted");
		return new IdValueSucceedDTO(taskId, courseTaskLoopRepo.countAllByTaskId(taskId), true);
	}

	@GetMapping("/task/{taskId}/poop/remove")
	@ResponseBody
	public IdValueSucceedDTO removePoop(@PathVariable(value = "taskId") long taskId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove poop newsId=" + taskId + "by userId=" + userId);
		CourseTaskPoop poop = courseTaskPoopRepo.getByTaskIdAndUserId(taskId, userId);
		if (poop == null) {
			return new IdValueSucceedDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId), false);
		}
		System.out.println(poop.toString());
		courseTaskPoopRepo.removeById(poop.getId());
		System.out.println("deleted");
		return new IdValueSucceedDTO(taskId, courseTaskPoopRepo.countAllByTaskId(taskId), true);
	}

	@GetMapping("/task/{taskId}/complete")
	@ResponseBody
	public IdSucceedDTO complete(@PathVariable(value = "taskId") long taskId,
	                      @CookieValue(value = "userId") long userId) {
		logger.info("complete taskId=" + taskId + "by userId=" + userId);
		if (courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return new IdSucceedDTO(taskId, false);
		}
		CourseTaskCompletion completion = new CourseTaskCompletion();
		completion.setTask(courseTaskRepo.getOne(taskId));
		completion.setUser(userRepo.getOne(userId));
		completion.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskCompletionRepo.save(completion);
		return new IdSucceedDTO(taskId, true);
	}

	@GetMapping("/task/{taskId}/undo")
	@ResponseBody
	public IdSucceedDTO undo(@PathVariable(value = "taskId") long taskId,
	                  @CookieValue(value = "userId") long userId) {
		logger.info("undo taskId=" + taskId + "by userId=" + userId);
		CourseTaskCompletion completion = courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId);
		if (completion == null) {
			return new IdSucceedDTO(taskId, false);
		}
		System.out.println(completion.toString());
		courseTaskPoopRepo.removeById(completion.getId());
		System.out.println("deleted");
		return new IdSucceedDTO(taskId, true);
	}
}
