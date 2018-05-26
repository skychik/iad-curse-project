package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.*;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@RepositoryRestController
@RequestMapping("/comments")
public class CommentsController {
	private final NewsRepo newsRepo;
	private final UserRepo userRepo;
	private final CommentRepo commentRepo;
	private final CommentLoopRepo commentLoopRepo;
	private final CommentPoopRepo commentPoopRepo;
	private final RepostRepo repostRepo;
	private final NewsLoopRepo newsLoopRepo;
	private final NewsPoopRepo newsPoopRepo;
	private final RepostLoopRepo repostLoopRepo;
	private final RepostPoopRepo repostPoopRepo;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	public CommentsController(NewsRepo newsRepo, UserRepo userRepo, CommentRepo commentRepo, CommentLoopRepo commentLoopRepo,
	                          CommentPoopRepo commentPoopRepo, RepostRepo repostRepo, NewsLoopRepo newsLoopRepo,
	                          NewsPoopRepo newsPoopRepo, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo) {
		this.newsRepo = newsRepo;
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
		this.commentLoopRepo = commentLoopRepo;
		this.commentPoopRepo = commentPoopRepo;
		this.repostRepo = repostRepo;
		this.newsLoopRepo = newsLoopRepo;
		this.newsPoopRepo = newsPoopRepo;
		this.repostLoopRepo = repostLoopRepo;
		this.repostPoopRepo = repostPoopRepo;
	}

	@GetMapping("/for")
	public @ResponseBody CommentsInfoDTO getCommentsForNewsId(@RequestParam(value = "newsId") long newsId,
	                                 @RequestParam(value = "userId") long userId,
	                                 @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                 @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		List<Comment> commentList = commentRepo.getAllForNewsId(newsId, of(pageNumber, pageSize,
				by(Sort.Order.by("onCommentId").nullsFirst(), Sort.Order.by("creationDate"))));

		List<CommentDTO> list = makeCommentDTOList(commentList, userId);
		list.sort((CommentDTO c1, CommentDTO c2) -> c1.getCreationDate().before(c2.getCreationDate()) ? 1 : 0);
		for (CommentDTO dto : list) {
			dto.sortComments();
		}
		return new CommentsInfoDTO(list, (long) commentList.size());
	}

	private List<CommentDTO> makeCommentDTOList(List<Comment> commentList, long userId) {
		List<CommentDTO> rootDTOs = new LinkedList<>();
		List<Comment> commentsToBeAdded = new LinkedList<>(commentList);
		boolean isSomeCommentAdded = true;

		while(!commentsToBeAdded.isEmpty() && isSomeCommentAdded) {
			isSomeCommentAdded = false;
			List<Comment> commentsToBeAddedBuff = new LinkedList<>(commentsToBeAdded);
			for (Comment comment : commentsToBeAdded) {
				CommentDTO dto = new CommentDTO(comment,
						commentLoopRepo.countAllByCommentId(comment.getId()),
						commentLoopRepo.getByCommentIdAndUserId(comment.getId(), userId) != null,
						commentPoopRepo.countAllByCommentId(comment.getId()),
						commentPoopRepo.getByCommentIdAndUserId(comment.getId(), userId) != null);

				// if no parents
				if (comment.getOnCommentId() == null) {
					logger.info("adding comment, id=" + dto.getId() + ":");
					rootDTOs.add(dto);
					commentsToBeAddedBuff.remove(comment);
					isSomeCommentAdded = true;
				} else if (addCommentToList(rootDTOs, dto, comment.getOnCommentId())) {
					commentsToBeAddedBuff.remove(comment);
					isSomeCommentAdded = true;
				} else logger.info("cant add comment, id=" + dto.getId());
			}
			commentsToBeAdded = commentsToBeAddedBuff;
		}
		if (!isSomeCommentAdded) {
			throw new IllegalArgumentException("There're comments without parents:" +
					commentsToBeAdded.toString() + "\nfor list:" + commentList.toString());
		}
		return rootDTOs;
	}

	// adding comment to its parent
	// answer - is added?
	private boolean addCommentToList(List<CommentDTO> addedDTOs, CommentDTO dtoToAdd, Long commentOnId) {
		Queue<CommentDTO> queue = new LinkedList<>(addedDTOs);
		while (!queue.isEmpty()) {
			Queue<CommentDTO> queueBuff = new LinkedList<>();
			for (CommentDTO dto : queue) {
				if (dto.getId().equals(commentOnId)) {
					logger.info("adjusting comment, id=" + dtoToAdd.getId() + " to comment, id=" + dto.getId());
					dto.addComment(dtoToAdd);
					return true;
				}
				queueBuff.addAll(dto.getComments());
			}
			queue = queueBuff;
		}
		return false;
	}

	@GetMapping("/{commentId}/loop/put")
	public @ResponseBody IdValueSucceedDTP putLoop(@PathVariable(value = "commentId") long commentId,
	                          @RequestParam(value = "userId") long userId) {
		logger.info("put loop commentId=" + commentId + "by userId=" + userId);
		if (commentLoopRepo.getByCommentIdAndUserId(commentId, userId) != null) {
			return new IdValueSucceedDTP(commentId, commentLoopRepo.countAllByCommentId(commentId), false);
		}
		CommentLoop loop = new CommentLoop();
		loop.setComment(commentRepo.getOne(commentId));
		loop.setUser(userRepo.getOne(userId));
		loop.setDate(new Timestamp(System.currentTimeMillis()));
		commentLoopRepo.save(loop);
		return new IdValueSucceedDTP(commentId, commentLoopRepo.countAllByCommentId(commentId), true);
	}

	@GetMapping("/{commentId}/poop/put")
	public @ResponseBody IdValueSucceedDTP putPoop(@PathVariable(value = "commentId") long commentId,
	                          @RequestParam(value = "userId") long userId) {
		logger.info("put poop commentId=" + commentId + "by userId=" + userId);
		if (commentPoopRepo.getByCommentIdAndUserId(commentId, userId) != null) {
			return new IdValueSucceedDTP(commentId, commentPoopRepo.countAllByCommentId(commentId), false);
		}
		CommentPoop poop = new CommentPoop();
		poop.setComment(commentRepo.getOne(commentId));
		poop.setUser(userRepo.getOne(userId));
		poop.setDate(new Timestamp(System.currentTimeMillis()));
		commentPoopRepo.save(poop);
		return new IdValueSucceedDTP(commentId, commentPoopRepo.countAllByCommentId(commentId), true);
	}

	@GetMapping("/{commentId}/loop/remove")
	public @ResponseBody IdValueSucceedDTP removeLoop(@PathVariable(value = "commentId") long commentId,
	                             @RequestParam(value = "userId") long userId) {
		logger.info("remove loop commentId=" + commentId + "by userId=" + userId);
		CommentLoop loop = commentLoopRepo.getByCommentIdAndUserId(commentId, userId);
		if (loop == null) {
			return new IdValueSucceedDTP(commentId, commentLoopRepo.countAllByCommentId(commentId), false);
		}
		commentLoopRepo.removeById(loop.getId());
		return new IdValueSucceedDTP(commentId, commentLoopRepo.countAllByCommentId(commentId), true);
	}

	@GetMapping("/{commentId}/poop/remove")
	public @ResponseBody IdValueSucceedDTP removePoop(@PathVariable(value = "commentId") long commentId,
	                             @RequestParam(value = "userId") long userId) {
		logger.info("remove poop commentId=" + commentId + "by userId=" + userId);
		CommentPoop poop = commentPoopRepo.getByCommentIdAndUserId(commentId, userId);
//		logger.info("poopid=" + poop);
		if (poop == null) {
			return new IdValueSucceedDTP(commentId, commentPoopRepo.countAllByCommentId(commentId), false);
		}
		commentPoopRepo.removeById(poop.getId());
		return new IdValueSucceedDTP(commentId, commentPoopRepo.countAllByCommentId(commentId),
				!commentPoopRepo.existsById(poop.getId()));
	}
}
