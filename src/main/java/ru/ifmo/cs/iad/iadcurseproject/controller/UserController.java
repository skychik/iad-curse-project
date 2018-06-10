package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@RepositoryRestController
@RequestMapping("/user")
public class UserController {
	private final UserRepo userRepo;
	private final CommentRepo commentRepo;
	//	private final RepostRepo repostRepo;
	private final NewsRepo newsRepo;
	private final NewsLoopRepo newsLoopRepo;
	private final NewsPoopRepo newsPoopRepo;
	//	private final PerformerRepo performerRepo;
//	private final RepostPoopRepo repostPoopRepo;
	private final CourseRepo courseRepo;
	private final CourseSubscriptionRepo courseSubscriptionRepo;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	@Autowired
	public UserController(UserRepo userRepo, CommentRepo commentRepo, /*RepostRepo repostRepo,*/ NewsRepo newsRepo,
	                      NewsLoopRepo newsLoopRepo, NewsPoopRepo newsPoopRepo, /*PerformerRepo performerRepo,*/
			/*RepostPoopRepo repostPoopRepo,*/ CourseRepo courseRepo,
			              CourseSubscriptionRepo courseSubscriptionRepo) {
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
//		this.repostRepo = repostRepo;
		this.newsRepo = newsRepo;
		this.newsLoopRepo = newsLoopRepo;
		this.newsPoopRepo = newsPoopRepo;
//		this.performerRepo = performerRepo;
//		this.repostPoopRepo = repostPoopRepo;
		this.courseRepo = courseRepo;
		this.courseSubscriptionRepo = courseSubscriptionRepo;
	}

	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity getProfileById(@PathVariable(value = "userId") long userId) {
		logger.info("getProfileById=" + userId);
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			return logAndGetBadRequestEntity("userId=" + userId + " doesn't exist");
		}
		return new ResponseEntity<>(new UserExtendedDTO(user.get()), HttpStatus.OK);
	}

	@GetMapping("/{userId}/news")
	@ResponseBody
	public ResponseEntity getNewsByUserId(@PathVariable(value = "userId") long userId,
	                                      @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                      @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("getNewsByUserId=" + userId);
		logger.info("bool=" + !userRepo.findById(userId).isPresent());
		logger.info(userRepo.findById(userId).get().toString());
		if (!userRepo.findById(userId).isPresent()) {
			return logAndGetBadRequestEntity("userId=" + userId + " doesn't exist");
		}

		List<News> newsList = newsRepo.getAllByUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));
		List<NewsForFeedDTO> newsDTOS = newsList.stream()
				.map((News news) -> new NewsForFeedDTO(news, (long) news.getComments().size(),
						(long) news.getNewsLoops().size(),
						newsLoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null,
						(long) news.getNewsPoops().size(),
						newsPoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null))
				.collect(Collectors.toList());

		return new ResponseEntity<>(newsDTOS, HttpStatus.OK);
	}

	@GetMapping("/{userId}/courses")
	@ResponseBody
	public ResponseEntity getCoursesByUserId(@PathVariable(value = "userId") long userId,
	                                         @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                         @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("getCoursesByUserId=" + userId);
		if (!userRepo.findById(userId).isPresent()) {
			return logAndGetBadRequestEntity("userId=" + userId + " doesn't exist");
		}

		List<Course> courseList = courseRepo.getAllByUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));

		List<CourseInfoDTO> courseDTOS = courseList.stream()
				.map((Course course) -> new CourseInfoDTO(course,
						(long) course.getTasks().stream().mapToInt((CourseTask t) -> t.getTaskLoops().size()).sum(),
						(long) course.getTasks().stream().mapToInt((CourseTask t) -> t.getTaskPoops().size()).sum(),
						(course.getTasks().stream().mapToInt((CourseTask t) ->
								t.getTaskCompletions().size()).sum() + 0.0) / (course.getTasks().size()) + 0.0,
						courseSubscriptionRepo.getByCourseIdAndUserId(course.getId(), userId) != null))
				.collect(Collectors.toList());

		return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
	}

	@GetMapping("/doesExist")
	@ResponseBody
	public boolean getLoginExistence(@RequestParam(value = "username") String username) {
		User user = userRepo.findByUsername(username);
		return user != null;
	}

	@GetMapping("/signin")
	@ResponseBody
	public ResponseEntity signin(@RequestParam(value = "username") String username,
	                             @RequestParam(value = "password") String password,
	                             HttpServletResponse response) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			return logAndGetBadRequestEntity("No such user");
		}
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Set-Cookie","userId=" + user.getId().toString());
		Cookie cookie = new Cookie("userId", user.getId().toString());
		cookie.setPath("/");
		cookie.setMaxAge(3000000);
		response.addCookie(cookie);
		return user.getPassword().equals(password) ? /*ResponseEntity.status(HttpStatus.OK).headers(headers).build()*/
				new ResponseEntity<>("", HttpStatus.OK) :
				new ResponseEntity<>("Wrong username and password", HttpStatus.BAD_REQUEST);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private ResponseEntity logAndGetBadRequestEntity(String msg) {
		logger.info(msg);
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}
}
