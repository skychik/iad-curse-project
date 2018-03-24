package ru.ifmo.cs.iad.iadcurseproject.service;

public interface NewsService {
	Iterable getAllNews(int userId, int fromNewsId, int toNewsId);

	// userId - for whom. That's for showing your friend in the list firstly
	Iterable getRepostsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId);
	Iterable getLoopsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId);
	Iterable getPoopsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId);
	int likeNewsByNewsIdAndUserId();
	int repostNewsByNewsIdAndUserId();
}
