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
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsPoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;


@RepositoryRestController
@RequestMapping("/news")
public class NewsController {
	private final NewsRepo newsRepo;
	private final UserRepo userRepo;
	private final CommentRepo commentRepo;
	//	private final RepostRepo repostRepo;
	private final NewsLoopRepo newsLoopRepo;
	private final NewsPoopRepo newsPoopRepo;
//	private final RepostLoopRepo repostLoopRepo;
//	private final RepostPoopRepo repostPoopRepo;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	@Autowired
	public NewsController(NewsRepo newsRepo, UserRepo userRepo, CommentRepo commentRepo, /*RepostRepo repostRepo,*/ NewsLoopRepo newsLoopRepo,
	                      NewsPoopRepo newsPoopRepo/*, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo*/) {
		this.newsRepo = newsRepo;
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
//		this.repostRepo = repostRepo;
		this.newsLoopRepo = newsLoopRepo;
		this.newsPoopRepo = newsPoopRepo;
//		this.repostLoopRepo = repostLoopRepo;
//		this.repostPoopRepo = repostPoopRepo;
	}

	@GetMapping("/for")
	@ResponseBody
	public List<NewsForFeedDTO> getNewsForUserId(@CookieValue(value = "userId") long userId,
	                                      @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                      @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		logger.info("news for userId=" + userId);
		List<News> newsList = newsRepo.getAllForUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.desc("creationDate"))));

		return newsList.stream()
				.map((News news) -> new NewsForFeedDTO(news, (long) news.getComments().size(),
						(long) news.getNewsLoops().size(), newsLoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null,
						(long) news.getNewsPoops().size(),newsPoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null))
				.collect(Collectors.toList());
	}

	@GetMapping("/{newsId}")
	@ResponseBody
	public ResponseEntity getNewsById(@PathVariable("newsId") long newsId,
	                                  @CookieValue(value = "userId") long userId) {
		logger.info("get newsId=" + newsId);
		News news = newsRepo.getOneByNewsId(newsId);
		if (news == null) {
			return logAndGetBadRequestEntity("newsId is null");
		}
		logger.info(news.toString());
		return new ResponseEntity<>(new NewsDTO(news, (long) news.getComments().size(),
				(long) news.getNewsLoops().size(), newsLoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null,
				(long) news.getNewsPoops().size(),newsPoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null),
				HttpStatus.OK);
	}

	//	@GetMapping("/{newsId}/comments")
//	public @ResponseBody Page<Repost> getCommentsByNewsId(
//			@PathVariable("newsId") long newsId,
//			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
//		return commentRepo.getAllByNewsId(newsId, of(pageNumber, pageSize, by("id")));
//	}

	@GetMapping("/{newsId}/comments_number")
	@ResponseBody
	public ResponseEntity getCommentsNumberByNewsId(@PathVariable("newsId") long newsId) {
		logger.info("getCommentsNumberByNewsId" + newsId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntity("No news with id=" + newsId);
		}
		return new ResponseEntity<>(commentRepo.countAllByNewsId(newsId), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/total_loops_number")
	@ResponseBody
	public ResponseEntity getTotalLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		logger.info("getTotalLoopsNumberByNewsId" + newsId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntity("No news with id=" + newsId);
		}
		return new ResponseEntity<>(newsLoopRepo.countAllByNewsId(newsId), HttpStatus.OK) ;//+ repostLoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/loops_number")
	@ResponseBody
	public ResponseEntity getLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		logger.info("getLoopsNumberByNewsId" + newsId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntity("No news with id=" + newsId);
		}
		return new ResponseEntity<>(newsLoopRepo.countAllByNewsId(newsId), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/total_poops_number")
	@ResponseBody
	public ResponseEntity getTotalPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		logger.info("getTotalPoopsNumberByNewsId" + newsId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntity("No news with id=" + newsId);
		}
		return new ResponseEntity<>(newsPoopRepo.countAllByNewsId(newsId), HttpStatus.OK);//+ repostPoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/poops_number")
	@ResponseBody
	public ResponseEntity getPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		logger.info("getPoopsNumberByNewsId" + newsId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntity("No news with id=" + newsId);
		}
		return new ResponseEntity<>(newsPoopRepo.countAllByNewsId(newsId), HttpStatus.OK);
	}

	@PostMapping(path="/post", consumes = "application/json", produces = "application/json")
	public ResponseEntity addNews(@RequestBody NewsPostedDTO newsDTO, @CookieValue(value = "userId") long userId) {
		logger.info("add news=" + newsDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			return logAndGetBadRequestEntity("User with id=" + userId + " doesn't exist");
		}
		News news = newsDTO.makeNews(user.get());
		News savedNews = newsRepo.save(news);
		logger.info("news saved=" + savedNews.toString());
		return new ResponseEntity<>(savedNews.getId().toString(), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/loop/put")
	@ResponseBody
	public ResponseEntity putLoop(@PathVariable(value = "newsId") long newsId,
	                                 @CookieValue(value = "userId") long userId) {
		logger.info("put loop newsId=" + newsId + "by userId=" + userId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntityWithIdValue("No news with id=" + newsId, null, null);
		}
		if (newsLoopRepo.getByNewsIdAndUserId(newsId, userId) != null) {
			return logAndGetBadRequestEntityWithIdValue("No loop on news with id=" + newsId,
					newsId, newsLoopRepo.countAllByNewsId(newsId));
		}
		NewsLoop loop = new NewsLoop();
		loop.setNews(newsRepo.getOne(newsId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		newsLoopRepo.save(loop);
		return new ResponseEntity<>(new IdValueDTO(newsId, newsLoopRepo.countAllByNewsId(newsId)), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/poop/put")
	@ResponseBody
	public ResponseEntity putPoop(@PathVariable(value = "newsId") long newsId,
	                          @CookieValue(value = "userId") long userId) {
		logger.info("put poop newsId=" + newsId + "by userId=" + userId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntityWithIdValue("No news with id=" + newsId, null, null);
		}
		if (newsPoopRepo.getByNewsIdAndUserId(newsId, userId) != null) {
			return logAndGetBadRequestEntityWithIdValue("No poop on news with id=" + newsId,
				newsId, newsPoopRepo.countAllByNewsId(newsId));
		}
		NewsPoop poop = new NewsPoop();
		poop.setNews(newsRepo.getOne(newsId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		newsPoopRepo.save(poop);
		return new ResponseEntity<>(new IdValueDTO(newsId, newsPoopRepo.countAllByNewsId(newsId)), HttpStatus.OK);
	}
	@GetMapping("/{newsId}/loop/remove")
	@ResponseBody
	public ResponseEntity removeLoop(@PathVariable(value = "newsId") long newsId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove loop newsId=" + newsId + "by userId=" + userId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntityWithIdValue("No news with id=" + newsId, null, null);
		}
		NewsLoop loop = newsLoopRepo.getByNewsIdAndUserId(newsId, userId);
		if (loop == null) {
			return logAndGetBadRequestEntityWithIdValue("No loop on news with id=" + newsId,
					newsId, newsLoopRepo.countAllByNewsId(newsId));
		}
		logger.info(loop.toString());
		newsLoopRepo.removeById(loop.getId());
		logger.info("deleted");
		return new ResponseEntity<>(new IdValueDTO(newsId, newsLoopRepo.countAllByNewsId(newsId)), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/poop/remove")
	@ResponseBody
	public ResponseEntity removePoop(@PathVariable(value = "newsId") long newsId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove poop newsId=" + newsId + "by userId=" + userId);
		if (!newsRepo.findById(newsId).isPresent()) {
			return logAndGetBadRequestEntityWithIdValue("No news with id=" + newsId, null, null);
		}
		NewsPoop poop = newsPoopRepo.getByNewsIdAndUserId(newsId, userId);
		if (poop == null) {
			return logAndGetBadRequestEntityWithIdValue("No poop on news with id=" + newsId,
					newsId, newsPoopRepo.countAllByNewsId(newsId));
		}
		logger.info(poop.toString());
		newsPoopRepo.removeById(poop.getId());
		logger.info("deleted");
		return new ResponseEntity<>(new IdValueDTO(newsId, newsPoopRepo.countAllByNewsId(newsId)), HttpStatus.OK);
	}

	public int likeNewsByNewsId() {
		return 0;
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
