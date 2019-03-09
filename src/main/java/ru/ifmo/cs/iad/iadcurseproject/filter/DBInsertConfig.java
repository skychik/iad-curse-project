//package ru.ifmo.cs.iad.iadcurseproject.filter;
//
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.ifmo.cs.iad.iadcurseproject.entity.User;
//import ru.ifmo.cs.iad.iadcurseproject.repository.*;
//
//public class DBInsertConfig implements InitializingBean {
//	private final NewsRepo newsRepo;
//	private final UserRepo userRepo;
//	private final CommentRepo commentRepo;
//	//	private final RepostRepo repostRepo;
//	private final NewsLoopRepo newsLoopRepo;
//	private final NewsPoopRepo newsPoopRepo;
//	//	private final RepostLoopRepo repostLoopRepo;
////	private final RepostPoopRepo repostPoopRepo;
//
//
//	@Autowired
//	public DBInsertConfig(NewsRepo newsRepo, UserRepo userRepo, CommentRepo commentRepo, /*RepostRepo repostRepo,*/ NewsLoopRepo newsLoopRepo,
//	                      NewsPoopRepo newsPoopRepo/*, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo*/) {
//		this.newsRepo = newsRepo;
//		this.userRepo = userRepo;
//		this.commentRepo = commentRepo;
////		this.repostRepo = repostRepo;
//		this.newsLoopRepo = newsLoopRepo;
//		this.newsPoopRepo = newsPoopRepo;
////		this.repostLoopRepo = repostLoopRepo;
////		this.repostPoopRepo = repostPoopRepo;
//	}
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		userRepo.save(
//	}
//
//	private User makeUser()
//}
