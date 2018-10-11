package com.example.commentstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "comments_model")
	public class CommentModel {
		
	   @Id
	   @Column(length = 36)
	   private String id;

	   @Column(length = 32)
	   private String pageId;

	   @Column(length = 32)
	   private String username;

	   @Column(length = 32)
	   private String emailAddress;

	   @Column
	   private boolean spam;

	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isSpam() {
		return spam;
	}

	public void setSpam(boolean spam) {
		this.spam = spam;
	}

	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", pageId=" + pageId + ", username="
				+ username + ", emailAddress=" + emailAddress + ", spam="
				+ spam + "]";
	}
	
	
	

	   
}
