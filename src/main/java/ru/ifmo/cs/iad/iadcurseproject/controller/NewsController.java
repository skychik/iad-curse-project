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
	private final RepostRepo repostRepo;
	private final NewsLoopRepo newsLoopRepo;
	private final NewsPoopRepo newsPoopRepo;
	private final RepostLoopRepo repostLoopRepo;
	private final RepostPoopRepo repostPoopRepo;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	@Autowired
	public NewsController(NewsRepo newsRepo, UserRepo userRepo, CommentRepo commentRepo, RepostRepo repostRepo, NewsLoopRepo newsLoopRepo,
	                      NewsPoopRepo newsPoopRepo, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo) {
		this.newsRepo = newsRepo;
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
		this.repostRepo = repostRepo;
		this.newsLoopRepo = newsLoopRepo;
		this.newsPoopRepo = newsPoopRepo;
		this.repostLoopRepo = repostLoopRepo;
		this.repostPoopRepo = repostPoopRepo;
	}

	@GetMapping("/for")
	@ResponseBody
	public List<NewsForFeedDTO> getNewsForUserId(@CookieValue(value = "userId") long userId,
	                                      @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                      @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		List<News> newsList = newsRepo.getAllForUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));

		return newsList.stream()
				.map((News news) -> new NewsForFeedDTO(news, (long) news.getComments().size(),
						(long) news.getNewsLoops().size(), newsLoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null,
						(long) news.getNewsPoops().size(),newsPoopRepo.getByNewsIdAndUserId(news.getId(), userId) != null))
				.collect(Collectors.toList());
	}

	@GetMapping("/{newsId}")
	@ResponseBody
	public ResponseEntity<NewsDTO> getNewsById(@PathVariable("newsId") long newsId,
	                                         @CookieValue(value = "userId") long userId) {
		logger.info("get newsId=" + newsId);
		News news = newsRepo.getOneByNewsId(newsId);
		if (news == null) {
			return new ResponseEntity<>(new NewsDTO(), HttpStatus.BAD_REQUEST);
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
	public long getCommentsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return commentRepo.countAllByNewsId(newsId);
	}

//	@GetMapping("/{newsId}/reposts")
//	public @ResponseBody Page<Repost> getRepostsByNewsId(
//			@PathVariable("newsId") long newsId,
//			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
//		return repostRepo.getAllByNewsId(newsId, of(pageNumber, pageSize, by("id")));
//	}

	@GetMapping("/{newsId}/reposts_number")
	@ResponseBody
	public long getRepostsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return repostRepo.countAllByNewsId(newsId);
	}

//	@GetMapping("/{newsId}/loops")
//	public @ResponseBody Page<NewsLoop> getLoopsByNewsId(
//			@PathVariable("newsId") long newsId,
//			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
//		return newsLoopRepo.getAllByNewsId(newsId, of(pageNumber, pageSize, by("id")));
//	}

	@GetMapping("/{newsId}/total_loops_number")
	@ResponseBody
	public long getTotalLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsLoopRepo.countAllByNewsId(newsId) + repostLoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/loops_number")
	@ResponseBody
	public long getLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsLoopRepo.countAllByNewsId(newsId);
	}

//	@GetMapping("/{newsId}/poops")
//	public @ResponseBody Page<NewsPoop> getPoopsByNewsId(
//			@PathVariable("newsId") long newsId,
//			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
//		return newsPoopRepo.getAllByNewsId(newsId, of(pageNumber, pageSize, by("id")));
//	}

	@GetMapping("/{newsId}/total_poops_number")
	@ResponseBody
	public long getTotalPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepo.countAllByNewsId(newsId) + repostPoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/poops_number")
	@ResponseBody
	public long getPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepo.countAllByNewsId(newsId);
	}

	@PostMapping(path="/post", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNews(@RequestBody NewsPostedDTO newsDTO, @CookieValue(value = "userId") long userId) {
		logger.info("add news=" + newsDTO.toString());
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			String str = "User with id=" + userId + " doesn't exist";
			logger.info(str);
			return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
		}
		News news = newsDTO.makeNews(user.get());
		News savedNews = newsRepo.save(news);
		logger.info("news saved=" + savedNews.toString());
		return new ResponseEntity<>(savedNews.getId().toString(), HttpStatus.OK);
	}

	@GetMapping("/{newsId}/loop/put")
	@ResponseBody
	public IdValueSucceedDTO putLoop(@PathVariable(value = "newsId") long newsId,
	                                 @CookieValue(value = "userId") long userId) {
		logger.info("put loop newsId=" + newsId + "by userId=" + userId);
		if (newsLoopRepo.getByNewsIdAndUserId(newsId, userId) != null) {
			return new IdValueSucceedDTO(newsId, newsLoopRepo.countAllByNewsId(newsId), false);
		}
		NewsLoop loop = new NewsLoop();
		loop.setNews(newsRepo.getOne(newsId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		newsLoopRepo.save(loop);
		return new IdValueSucceedDTO(newsId, newsLoopRepo.countAllByNewsId(newsId), true);
	}

	@GetMapping("/{newsId}/poop/put")
	@ResponseBody
	public IdValueSucceedDTO putPoop(@PathVariable(value = "newsId") long newsId,
	                          @CookieValue(value = "userId") long userId) {
		logger.info("put poop newsId=" + newsId + "by userId=" + userId);
		if (newsPoopRepo.getByNewsIdAndUserId(newsId, userId) != null) {
			return new IdValueSucceedDTO(newsId, newsPoopRepo.countAllByNewsId(newsId), false);
		}
		NewsPoop poop = new NewsPoop();
		poop.setNews(newsRepo.getOne(newsId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		newsPoopRepo.save(poop);
		return new IdValueSucceedDTO(newsId, newsPoopRepo.countAllByNewsId(newsId), true);
	}
	@GetMapping("/{newsId}/loop/remove")
	@ResponseBody public IdValueSucceedDTO removeLoop(@PathVariable(value = "newsId") long newsId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove loop newsId=" + newsId + "by userId=" + userId);
		NewsLoop loop = newsLoopRepo.getByNewsIdAndUserId(newsId, userId);
		if (loop == null) {
			return new IdValueSucceedDTO(newsId, newsLoopRepo.countAllByNewsId(newsId), false);
		}
		System.out.println(loop.toString());
		newsLoopRepo.removeById(loop.getId());
		System.out.println("deleted");
		return new IdValueSucceedDTO(newsId, newsLoopRepo.countAllByNewsId(newsId), true);
	}

	@GetMapping("/{newsId}/poop/remove")
	@ResponseBody
	public IdValueSucceedDTO removePoop(@PathVariable(value = "newsId") long newsId,
	                             @CookieValue(value = "userId") long userId) {
		logger.info("remove poop newsId=" + newsId + "by userId=" + userId);
		NewsPoop poop = newsPoopRepo.getByNewsIdAndUserId(newsId, userId);
		if (poop == null) {
			return new IdValueSucceedDTO(newsId, newsPoopRepo.countAllByNewsId(newsId), false);
		}
		newsPoopRepo.removeById(poop.getId());
		return new IdValueSucceedDTO(newsId, newsPoopRepo.countAllByNewsId(newsId), true);
	}

	public int likeNewsByNewsId() {
		return 0;
	}
}
