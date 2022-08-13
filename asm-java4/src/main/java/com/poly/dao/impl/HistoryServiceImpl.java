package com.poly.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.poly.dao.HistoryDao;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;

public class HistoryServiceImpl implements HistoryService {

	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();
	
	
	public HistoryServiceImpl( ) {
		dao = new HistoryDaoImpl();
	}

	@Override
	public List<History> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(int userId, int videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History history = new History();
		history.setUser(user);
		history.setVideo(video);
		history.setIsLiked(Boolean.FALSE);
		return dao.create(history);
	}

	@Override
	public boolean updateLikeOrUnLike(User user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existHistory.getIsLiked() == false) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setIsLiked(null);
		}
		History updateHistory = dao.update(existHistory);
		return updateHistory != null ? true : false;
	}

}
