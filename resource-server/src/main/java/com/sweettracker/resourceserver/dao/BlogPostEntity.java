package com.sweettracker.resourceserver.dao;

import com.sweettracker.resourceserver.controller.BlogPostRequest;
import com.sweettracker.resourceserver.domain.BlogPost;
import com.sweettracker.resourceserver.domain.BlogPostList.BlogPostListItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "BLOG_POST")
@NoArgsConstructor
public class BlogPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BlogPostCommentEntity> comments = new ArrayList<>();

    @Builder
    public BlogPostEntity(Long id, String title, String content,
        List<BlogPostCommentEntity> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public void setRelation() {
        for (BlogPostCommentEntity comment : comments) {
            comment.setBlogPost(this);
        }
    }

    public BlogPost update(BlogPostRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        return toDomain();
    }

    public BlogPostListItem toListItemDomain() {
        return BlogPostListItem.builder()
            .id(id)
            .title(title)
            .build();
    }


    public BlogPost toDomain() {
        return BlogPost.builder()
            .id(id)
            .title(title)
            .content(content)
            .comments(comments.stream()
                .map(BlogPostCommentEntity::toDomain)
                .toList())
            .build();
    }
}
