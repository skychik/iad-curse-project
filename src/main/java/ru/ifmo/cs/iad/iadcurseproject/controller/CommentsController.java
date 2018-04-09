package ru.ifmo.cs.iad.iadcurseproject.controller;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.dto.CommentDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.CommentsInfoDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsDTO;
import ru.ifmo.cs.iad.iadcurseproject.dto.NewsForFeedDTO;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentPoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@RepositoryRestController
@RequestMapping("/comments")
public class CommentsController {
	private final NewsRepo newsRepo;
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

	public CommentsController(NewsRepo newsRepo, CommentRepo commentRepo, CommentLoopRepo commentLoopRepo,
	                          CommentPoopRepo commentPoopRepo, RepostRepo repostRepo, NewsLoopRepo newsLoopRepo,
	                          NewsPoopRepo newsPoopRepo, RepostLoopRepo repostLoopRepo, RepostPoopRepo repostPoopRepo) {
		this.newsRepo = newsRepo;
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
	public @ResponseBody CommentsInfoDTO getNewsForUserId(@RequestParam(value = "newsId") long newsId,
	                                 @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
	                                 @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		List<Comment> commentList = commentRepo.getAllForNewsId(newsId, of(pageNumber, pageSize,
				by(Sort.Order.by("onCommentId").nullsFirst(), Sort.Order.by("creationDate"))));

		List<CommentDTO> list = makeCommentDTOList(commentList);
		list.sort((CommentDTO c1, CommentDTO c2) -> c1.getCreationDate().before(c2.getCreationDate()) ? 1 : 0);
		for (CommentDTO dto : list) {
			dto.sortComments();
		}
		return new CommentsInfoDTO(list, (long) commentList.size());
	}

	private List<CommentDTO> makeCommentDTOList(List<Comment> commentList) {
		List<CommentDTO> rootDTOs = new LinkedList<>();
		List<Comment> commentsToBeAdded = new LinkedList<>(commentList);
		boolean someCommentWasAdded = true;

		while(!commentsToBeAdded.isEmpty() && someCommentWasAdded) {
			someCommentWasAdded = false;
			List<Comment> commentsToBeAddedBuff = new LinkedList<>(commentsToBeAdded);
			for (Comment comment : commentsToBeAdded) {
				CommentDTO dto = new CommentDTO(comment, commentLoopRepo.countAllByCommentId(comment.getId()),
						commentPoopRepo.countAllByCommentId(comment.getId()));

				// if no parents
				if (comment.getOnCommentId() == null) {
					logger.info("adding comment, id=" + dto.getId());
					rootDTOs.add(dto);
					commentsToBeAddedBuff.remove(comment);
					someCommentWasAdded = true;
				} else if (addCommentToList(rootDTOs, dto, comment.getOnCommentId())) {
					commentsToBeAddedBuff.remove(comment);
					someCommentWasAdded = true;
				} else logger.info("cant add comment, id=" + dto.getId());
			}
			commentsToBeAdded = commentsToBeAddedBuff;
		}
		if (!someCommentWasAdded) {
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
}
