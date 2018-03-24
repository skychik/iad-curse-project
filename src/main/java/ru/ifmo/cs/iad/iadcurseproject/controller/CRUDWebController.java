package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Repost;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;
import ru.ifmo.cs.iad.iadcurseproject.service.NewsService;
import ru.ifmo.cs.iad.iadcurseproject.service.impl.NewsServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CRUDWebController {
	@Autowired
	public CRUDWebController(CommentPoopRepository commentPoopRepository,
	                         AchievementReceivingRepository achievementReceivingRepository,
	                         AchievementRepository achievementRepository, CommentRepository commentRepository,
	                         CommentLoopRepository commentLoopRepository,
	                         JoiningPerformerRepository joiningPerformerRepository,
	                         NewsRepository newsRepository, RepostPoopRepository repostPoopRepository,
	                         UserRepository userRepository, NewsLoopRepository newsLoopRepository,
	                         NewsPoopRepository newsPoopRepository, PerformerRepository performerRepository,
	                         RepostLoopRepository repostLoopRepository, SubscriptionRepository subscriptionRepository,
	                         RepostRepository repostRepository) {
		newsService = new NewsServiceImpl(newsRepository, userRepository, subscriptionRepository);
	}

	private NewsService newsService;

	@RequestMapping(value = "/all_news", method = RequestMethod.GET)
	public Iterable getAllNews(@RequestParam(value = "userId", required = false, defaultValue = "-1") int userId,
	                             @RequestParam(value = "fromNewsId", required = false, defaultValue = "0") int fromNewsId,
	                             @RequestParam(value = "total") int total) {
		return newsService.getAllNews(userId, fromNewsId, total);
	}

//	@RequestMapping(value = "/")
}
