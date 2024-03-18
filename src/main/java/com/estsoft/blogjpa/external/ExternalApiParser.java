package com.estsoft.blogjpa.external;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class ExternalApiParser { // 외부 API 파서
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ArticleService articleService;

    public ExternalApiParser(RestTemplate restTemplate, ObjectMapper objectMapper, ArticleService articleService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.articleService = articleService;
    }

    // 외부 API 호출 및 파싱
    public void parse() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String json = response.getBody();
            log.info("API 호출이 성공했습니다.");

            try {
                List<LinkedHashMap<String, Object>> dataList = objectMapper.readValue(json, List.class);

                for (LinkedHashMap<String, Object> data : dataList) {
                    String title = (String) data.get("title");
                    String content = (String) data.get("body");
                    //log.info("title: {}, content: {}", title, content);

                    // Article 객체 생성 후 저장
                    AddArticleRequest request = new AddArticleRequest();
                    request.setTitle(title);
                    request.setContent(content);
                    Article savedArticle = articleService.save(request);
                    log.info("Article saved with id: {}", savedArticle.getId());
                }
            } catch (Exception e) {
                log.error("JSON 파싱 중 오류 발생: {}", e.getMessage());
            }
        } else {
            log.error("API 호출이 실패했습니다. 상태 코드: {}", response.getStatusCodeValue());
        }
    }
}
