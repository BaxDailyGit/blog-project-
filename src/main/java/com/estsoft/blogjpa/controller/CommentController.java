package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.AddCommentRequest;
import com.estsoft.blogjpa.service.CommentService;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.domain.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/articles/{articleId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long articleId, @RequestBody AddCommentRequest request) {
        Comment comment = commentService.addComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommentResponse(comment));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<CommentResponse> comments = commentService.getCommentsByArticleId(articleId)
                .stream().map(CommentResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(comments);
    }
}
