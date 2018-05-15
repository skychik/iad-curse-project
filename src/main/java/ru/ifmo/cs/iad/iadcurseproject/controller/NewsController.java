package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsForFeedDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsPostedDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.UserRegistrationDTO;
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
import static org.springframework.data.util.Streamable.empty;


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
	public @ResponseBody List<NewsForFeedDTO> getNewsForUserId(@RequestParam(value = "userId") long userId,
	                                      @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                      @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		List<News> newsList = newsRepo.getAllForUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));

		return newsList.stream()
				.map((News news) -> new NewsForFeedDTO(news, (long) news.getComments().size(),
						(long) news.getNewsLoops().size(), (long) news.getNewsPoops().size(),
						(long) news.getReposts().size()))
				.collect(Collectors.toList());
	}

	@GetMapping("/{newsId}")
	public @ResponseBody NewsDTO getNewsById(@PathVariable("newsId") long newsId) {
		News news = newsRepo.getOneByNewsId(newsId);
		if (news == null) return new NewsDTO();
		return new NewsDTO(news, (long) news.getComments().size(), (long) news.getNewsLoops().size(),
				(long) news.getNewsPoops().size(), (long) news.getReposts().size());
	}

	//	@GetMapping("/{newsId}/comments")
//	public @ResponseBody Page<Repost> getCommentsByNewsId(
//			@PathVariable("newsId") long newsId,
//			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
//		return commentRepo.getAllByNewsId(newsId, of(pageNumber, pageSize, by("id")));
//	}

	@GetMapping("/{newsId}/comments_number")
	public @ResponseBody long getCommentsNumberByNewsId(@PathVariable("newsId") long newsId) {
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
	public @ResponseBody long getRepostsNumberByNewsId(@PathVariable("newsId") long newsId) {
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
	public @ResponseBody long getTotalLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsLoopRepo.countAllByNewsId(newsId) + repostLoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/loops_number")
	public @ResponseBody long getLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
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
	public @ResponseBody long getTotalPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepo.countAllByNewsId(newsId) + repostPoopRepo.countAllByNewsId(newsId);
	}

	@GetMapping("/{newsId}/poops_number")
	public @ResponseBody long getPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepo.countAllByNewsId(newsId);
	}

	@PostMapping(path="/post", consumes = "application/json", produces = "application/json")
	public String addMember(@RequestBody NewsPostedDTO newsDTO) {
		logger.info("add news=" + newsDTO.toString());
		Optional<User> user = userRepo.findById(newsDTO.getAuthorId());
		if (!user.isPresent()) {
			logger.info("User with id=" + newsDTO.getAuthorId() + " doesn't exist");
			return null;
		}
		News news = newsDTO.makeNews(user.get());
		News savedNews = newsRepo.save(news);
		logger.info("news saved=" + savedNews.toString());
		return savedNews.getId().toString();
	}

	@GetMapping("/{newsId}/loop")
	public @ResponseBody Boolean putLoop(@PathVariable(value = "newsId") long newsId,
	                        @RequestParam(value = "userId") long userId) {
		logger.info("loop newsId=" + newsId + "by userId=" + userId);
		NewsLoop loop = new NewsLoop();
		loop.setNews(newsRepo.getOne(newsId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		newsLoopRepo.save(loop);
		return true;
	}

	@GetMapping("/{newsId}/poop")
	public @ResponseBody Boolean putPoop(@PathVariable(value = "newsId") long newsId,
	                                     @RequestParam(value = "userId") long userId) {
		logger.info("poop newsId=" + newsId + "by userId=" + userId);
		NewsPoop poop = new NewsPoop();
		poop.setNews(newsRepo.getOne(newsId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		newsPoopRepo.save(poop);
		return true;
	}

	public int likeNewsByNewsId() {
		return 0;
	}
}
