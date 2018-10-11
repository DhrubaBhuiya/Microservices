package com.example.commentstore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.commentstore.model.CommentModel;
import com.example.commentstore.service.CommentService;

@RestController
public class CommentStoreApp {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value="/add")
	public CommentModel add(@RequestBody CommentModel model){
		CommentModel model1 = null;
		try {
			model1 = commentService.put(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model1;
	}
	@GetMapping(value="/get/{id}")
	public CommentModel get(@PathVariable("id") String id){
		return commentService.get(id);
	}
	@GetMapping(value="/get/page/{pageId}")
	public List<CommentModel> getByPage(@PathVariable("pageId") String pageId){
		try {
			return commentService.list(pageId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping(value="/get/spam/{pageId}")
	public List<CommentModel> getSpamByPage(@PathVariable("pageId") String pageId){
		try {
			return commentService.listSpamComments(pageId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id){
		commentService.delete(id);
	}
}
