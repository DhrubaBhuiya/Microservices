package com.example.commentstore.service;

import java.io.IOException;
import java.util.List;

import com.example.commentstore.model.CommentModel;

public interface CommentService {
    CommentModel put(CommentModel model) throws IOException;
    List<CommentModel> list(String pageId) throws IOException;
    CommentModel get(String id);
    List<CommentModel> listSpamComments(String pageId) throws IOException;
    void delete(String id);
}

