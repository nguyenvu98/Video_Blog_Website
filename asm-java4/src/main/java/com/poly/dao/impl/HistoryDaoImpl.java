package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.HistoryDao;
import com.poly.dao.VideoDao;
import com.poly.entity.History;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.video.isActive = 1"
				+ " ORDER BY o.viewDate DESC";	
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1"
				+ " AND o.video.isActive = 1"
				+ " ORDER BY o.viewDate DESC";	
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserIdAndVideoId(int userId, int videoId) {
		String sql = "SELECT o FROM History o WHERE o.user.userId = ?0 AND o.video.videoId = ?1"
				+ " AND o.video.isActive = 1";	
		return super.findOne(History.class, sql, userId,videoId);
	}

}
