package com.sweettracker.resourceserver.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogPostComment {

    private Long id;
    private String content;

    @Builder
    public BlogPostComment(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
