package com.sweettracker.resourceserver.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogPost {

    private Long id;
    private String title;
    private String content;
    private List<BlogPostComment> comments;

    @Builder
    public BlogPost(Long id, String title, String content, List<BlogPostComment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }
}
