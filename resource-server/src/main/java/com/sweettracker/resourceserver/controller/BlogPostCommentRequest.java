package com.sweettracker.resourceserver.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogPostCommentRequest {

    private String content;

    @Builder
    public BlogPostCommentRequest(String content) {
        this.content = content;
    }

}
