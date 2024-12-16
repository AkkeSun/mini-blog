package com.sweettracker.resourceserver.dao;

import com.sweettracker.resourceserver.domain.BlogPostComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "BLOG_POST_COMMENT")
@NoArgsConstructor
public class BlogPostCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLOG_POST_ID")
    private BlogPostEntity blogPost;

    @Builder
    public BlogPostCommentEntity(Long id, String content, BlogPostEntity blogPost) {
        this.id = id;
        this.content = content;
        this.blogPost = blogPost;
    }

    public BlogPostComment toDomain() {
        return BlogPostComment.builder()
            .id(id)
            .content(content)
            .build();
    }
}
