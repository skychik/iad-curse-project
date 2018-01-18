package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.repository.NewsRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.PerformerRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.UserRepository;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CRUDWebController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PerformerRepository performerRepository;
	@Autowired
	private NewsRepository newsRepository;

//	@RequestMapping(value = "/news", method = RequestMethod.GET)
//	public List<News> getAllNews() {
//		return newsRepository.findAll();
//	}
}
