package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsForFeedDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.PerformerDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.ProfileDTO;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@RepositoryRestController
@RequestMapping("/user")
public class UserController {
	private final UserRepo userRepo;
	private final CommentRepo commentRepo;
	private final RepostRepo repostRepo;
	private final NewsRepo newsRepo;
	private final NewsPoopRepo newsPoopRepo;
	private final PerformerRepo performerRepo;
	private final RepostPoopRepo repostPoopRepo;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	@Autowired
	public UserController(UserRepo userRepo, CommentRepo commentRepo, RepostRepo repostRepo, NewsRepo newsRepo,
	                      NewsPoopRepo newsPoopRepo, PerformerRepo performerRepo, RepostPoopRepo repostPoopRepo) {
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
		this.repostRepo = repostRepo;
		this.newsRepo = newsRepo;
		this.newsPoopRepo = newsPoopRepo;
		this.performerRepo = performerRepo;
		this.repostPoopRepo = repostPoopRepo;
	}

	@GetMapping("/{userId}")
	public @ResponseBody ProfileDTO getProfileById(@PathVariable(value = "userId") long userId,
	                                               @RequestParam(value = "size",
			                                               required = false, defaultValue = "15") int pageSize,
	                                               @RequestParam(value = "page",
			                                               required = false, defaultValue = "0") int pageNumber) {
		User user = userRepo.getOne(userId);

		List<News> newsList = newsRepo.getAllByUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("alteringDate").nullsLast(), Sort.Order.by("creationDate"))));
		List<NewsForFeedDTO> newsDTOS = newsList.stream()
				.map((News news) -> new NewsForFeedDTO(news, (long) news.getComments().size(),
						(long) news.getNewsLoops().size(), (long) news.getNewsPoops().size(),
						(long) news.getReposts().size()))
				.collect(Collectors.toList());
		return new ProfileDTO(user, newsDTOS);
	}

	@GetMapping("/{userId}/performers")
	public @ResponseBody List<PerformerDTO> getPerformersByUserId(@PathVariable(value = "userId") long userId,
	                           @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                           @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {

		List<Performer> performerList = performerRepo.getAllByUserId(userId, of(pageNumber, pageSize,
				by(Sort.Order.by("breakupDate").nullsLast(), Sort.Order.by("creationDate"))));

		return performerList.stream().map(PerformerDTO::new).collect(Collectors.toList());
	}
}
