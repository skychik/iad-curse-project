package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsForFeedDTO;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.util.Streamable.empty;


@RepositoryRestController
@RequestMapping("/news")
public class NewsController {
	private final NewsRepo newsRepo;
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
	public NewsController(NewsRepo newsRepo, CommentRepo commentRepo, RepostRepo repostRepo, NewsLoopRepo newsLoopRepo,
	                      NewsPoopRepo newsPoopRepo, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo) {
		this.newsRepo = newsRepo;
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

	public int likeNewsByNewsId() {
		return 0;
	}
}
