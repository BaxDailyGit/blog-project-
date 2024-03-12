package com.estsoft.blogjpa.dto;

public class UpdateArticleRequest {
    private String title;
    private String content;

    public UpdateArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}