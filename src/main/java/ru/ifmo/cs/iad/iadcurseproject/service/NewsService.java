package ru.ifmo.cs.iad.iadcurseproject.service;

public interface NewsService {
	Iterable getAllNews(int userId, int fromNewsId, int toNewsId);

}
