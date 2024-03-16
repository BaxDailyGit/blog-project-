package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.external.ExternalApiParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonParseTestController {
    private final ExternalApiParser externalApiParser;

    public JsonParseTestController(ExternalApiParser externalApiParser) {
        this.externalApiParser = externalApiParser;
    }

    // 외부 API 호출 테스트
    @GetMapping("/parse")
    public ResponseEntity<Void> parse() {
        externalApiParser.parse();
        return ResponseEntity.ok().build();
    }
}
