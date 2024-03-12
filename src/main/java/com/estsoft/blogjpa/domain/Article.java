package com.estsoft.blogjpa.domain;

import com.estsoft.blogjpa.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toResponse() {
        return new ArticleResponse(this);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}