package com.example.commentstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.commentstore.model.CommentModel;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentModelRepository commentModelRepository;
	
	
	public CommentServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentServiceImpl(CommentModelRepository commentModelRepository) {
		super();
		this.commentModelRepository = commentModelRepository;
	}

	@Override
	public CommentModel put(CommentModel model) throws IOException {
		return commentModelRepository.save(model);
	}

	@Override
	public List<CommentModel> list(String pageId) throws IOException {
		return commentModelRepository.findByPageId(pageId);
	}

	@Override
	public CommentModel get(String id) {
		return commentModelRepository.findById(id).get();
	}

	@Override
	public List<CommentModel> listSpamComments(String pageId)
			throws IOException {
		return commentModelRepository.findByPageIdAndSpamIsTrue(pageId);
	}

	@Override
	public void delete(String id) {
		commentModelRepository.deleteById(id);
		
	}

}
