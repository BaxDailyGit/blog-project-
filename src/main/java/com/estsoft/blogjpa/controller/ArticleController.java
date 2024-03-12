package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.UpdateArticleRequest;
import com.estsoft.blogjpa.service.ArticleService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController        // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // HTTP요청이 'POST /api/articles' 경로일 때 해당 메소드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) { // RequestBody로 요청 본문 값 매핑
        Article article = articleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.toResponse());
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> list = articleService.findAll()
                .stream().map(ArticleResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    //id에 해당하는 article 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(article.toResponse());
    }


    //id에 해당하는 article 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticles(@PathVariable Long id) {
        articleService.delete(id);

        return ResponseEntity.ok().build();
    }

    //id에 해당하는 article 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = articleService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedArticle);
    }

    //JPQL
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Void> updateTitle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        articleService.updateTitle(id, request.getTitle());
        return ResponseEntity.ok().build();
    }
}