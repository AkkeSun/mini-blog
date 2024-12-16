package com.sweettracker.resourceserver.controller;

import com.sweettracker.resourceserver.dao.BlogPostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogPostRequest {

    private String title;
    private String content;

    @Builder
    public BlogPostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BlogPostEntity toEntity() {
        return BlogPostEntity.builder()
            .title(title)
            .content(content)
            .build();
    }

    public void update(BlogPostRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
