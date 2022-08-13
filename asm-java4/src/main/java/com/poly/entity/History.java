package com.poly.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "history")
public class History {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInit"})
	private User user;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInit"})
	private Video video;
	
	@Column(name = "viewDate")
	@CreationTimestamp
	private java.sql.Timestamp viewDate;

	@Column(name = "isLiked")
	private Boolean isLiked;
	
	@Column(name = "likeDate")
	private java.sql.Timestamp likeDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public java.sql.Timestamp getViewDate() {
		return viewDate;
	}
	public void setViewDate(java.sql.Timestamp viewDate) {
		this.viewDate = viewDate;
	}
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	public java.sql.Timestamp getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(java.sql.Timestamp likeDate) {
		this.likeDate = likeDate;
	}

	
}
