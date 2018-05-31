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
	public @ResponseBody
	List<CourseTaskPreviewDTO> getCommentsForNewsId(@RequestParam(value = "userId") long userId,
	                                                @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                                @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
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
	public @ResponseBody
	CourseTaskDTO getTaskById(@PathVariable("taskId") long taskId,
	                          @RequestParam(value = "userId") long userId) {
		CourseTask task = courseTaskRepo.getOneByTaskId(taskId);
		if (task == null) return new CourseTaskDTO();
		return new CourseTaskDTO(task, (long) task.getTaskComments().size(),
				(long) task.getTaskLoops().size(), courseTaskLoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				(long) task.getTaskPoops().size(), courseTaskPoopRepo.getByTaskIdAndUserId(task.getId(), userId) != null,
				courseTaskCompletionRepo.getByTaskIdAndUserId(task.getId(), userId) != null);
	}

	@GetMapping("/task/{taskId}/comments_number")
	public @ResponseBody long getCommentsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return taskCommentRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/task/{taskId}/loops_number")
	public @ResponseBody long getLoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return courseTaskLoopRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/task/{taskId}/poops_number")
	public @ResponseBody long getPoopsNumberByTaskId(@PathVariable("taskId") long taskId) {
		return courseTaskPoopRepo.countAllByTaskId(taskId);
	}

	@GetMapping("/title_exists")
	public @ResponseBody Boolean doesTitleExist(@RequestParam("userId") long userId,
	                                            @RequestParam("title") String title) {
		return courseRepo.findByAuthorIdAndTitle(userId, title) != null;
	}

	@PostMapping(path="/task/post", consumes = "application/json", produces = "application/json")
	public String addNewCourseWithTask(@RequestBody InitCourseTaskPostedDTO taskDTO) {
		logger.info("add new course with task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(taskDTO.getAuthorId());
		if (!user.isPresent()) {
			logger.info("User with id=" + taskDTO.getAuthorId() + " doesn't exist");
			return null;
		}
		Course course = courseRepo.findByAuthorIdAndTitle(taskDTO.getAuthorId(), taskDTO.getCourse().getTitle());
		if (course != null) {
			logger.info("User with id=" + taskDTO.getAuthorId() + " already has course with title " + taskDTO.getTitle());
			return null;
		}
		CourseTask task = taskDTO.makeTask(user.get());
		CourseTask savedTask = courseTaskRepo.save(task);
		logger.info("task saved=" + savedTask.toString());
		return savedTask.getId().toString();
	}

	@PostMapping(path="/task/post", consumes = "application/json", produces = "application/json")
	public String addTask(@RequestBody CourseTaskPostedDTO taskDTO) {
		logger.info("add task=" + taskDTO.toString());
		Optional<User> user = userRepo.findById(taskDTO.getAuthorId());
		Optional<Course> course = courseRepo.findById(taskDTO.getCourseId());
		if (!user.isPresent()) {
			logger.info("User with id=" + taskDTO.getAuthorId() + " doesn't exist");
			return null;
		}
		if (!course.isPresent()) {
			logger.info("Course with id=" + taskDTO.getCourseId() + " doesn't exist");
			return null;
		}
		if (course.get().getAuthor() != user.get()) {
			logger.info("Course with id=" + taskDTO.getCourseId() + " doesn't own user with id=" + taskDTO.getAuthorId());
			return null;
		}
		CourseTask task = taskDTO.makeTask(course.get());
		CourseTask savedTask = courseTaskRepo.save(task);
		logger.info("task saved=" + savedTask.toString());
		return savedTask.getId().toString();
	}

	@GetMapping("/task/{taskId}/loop/put")
	public @ResponseBody
	IdValueSucceedDTO putLoop(@PathVariable(value = "taskId") long taskId,
	                          @RequestParam(value = "userId") long userId) {
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
	public @ResponseBody
	IdValueSucceedDTO putPoop(@PathVariable(value = "taskId") long taskId,
	                          @RequestParam(value = "userId") long userId) {
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
	public @ResponseBody
	IdValueSucceedDTO removeLoop(@PathVariable(value = "taskId") long taskId,
	                             @RequestParam(value = "userId") long userId) {
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
	public @ResponseBody
	IdValueSucceedDTO removePoop(@PathVariable(value = "taskId") long taskId,
	                             @RequestParam(value = "userId") long userId) {
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
	public @ResponseBody
	ValueSucceedDTO complete(@PathVariable(value = "taskId") long taskId,
	                         @RequestParam(value = "userId") long userId) {
		logger.info("complete taskId=" + taskId + "by userId=" + userId);
		if (courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId) != null) {
			return new ValueSucceedDTO(taskId, false);
		}
		CourseTaskCompletion completion = new CourseTaskCompletion();
		completion.setTask(courseTaskRepo.getOne(taskId));
		completion.setUser(userRepo.getOne(userId));
		completion.setDate(new Timestamp(System.currentTimeMillis()));
		courseTaskCompletionRepo.save(completion);
		return new ValueSucceedDTO(taskId, true);
	}

	@GetMapping("/task/{taskId}/undo")
	public @ResponseBody
	ValueSucceedDTO undo(@PathVariable(value = "taskId") long taskId,
	                     @RequestParam(value = "userId") long userId) {
		logger.info("undo taskId=" + taskId + "by userId=" + userId);
		CourseTaskCompletion completion = courseTaskCompletionRepo.getByTaskIdAndUserId(taskId, userId);
		if (completion == null) {
			return new ValueSucceedDTO(taskId, false);
		}
		System.out.println(completion.toString());
		courseTaskPoopRepo.removeById(completion.getId());
		System.out.println("deleted");
		return new ValueSucceedDTO(taskId, true);
	}
}
