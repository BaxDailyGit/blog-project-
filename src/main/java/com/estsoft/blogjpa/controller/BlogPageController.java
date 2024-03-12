package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.ArticleViewResponse;
import com.estsoft.blogjpa.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogPageController {
    private ArticleService articleService;

    public BlogPageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articles = articleService.findAll().stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);   // model에 블로그 글 리스트 저장

        return "articleList";   // articleList.html라는 뷰 조회
    }

    // id에 해당하는 article 조회
    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}