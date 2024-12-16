package com.sweettracker.resourceserver.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogPostList {

    private int nowPage;
    private int totalPage;
    private List<BlogPostListItem> posts;

    @Builder
    public BlogPostList(int nowPage, int totalPage, List<BlogPostListItem> posts) {
        this.nowPage = nowPage;
        this.totalPage = totalPage;
        this.posts = posts;
    }

    @Getter
    @NoArgsConstructor
    public static class BlogPostListItem {

        private Long id;
        private String title;

        @Builder
        public BlogPostListItem(Long id, String title) {
            this.id = id;
            this.title = title;
        }
    }

}
