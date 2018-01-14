package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.cs.iad.iadcurseproject.repository.NewsRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.PerformerRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.UserRepository;

@RestController
public class CRUDWebController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PerformerRepository performerRepository;
	@Autowired
	private NewsRepository newsRepository;
}
