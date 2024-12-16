package com.sweettracker.resourceserver.controller;

import com.sweettracker.resourceserver.dao.BlogPostCommentEntity;
import com.sweettracker.resourceserver.dao.BlogPostCommentRepository;
import com.sweettracker.resourceserver.dao.BlogPostEntity;
import com.sweettracker.resourceserver.dao.BlogPostRepository;
import com.sweettracker.resourceserver.domain.BlogPostComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class BlogPostCommentController {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostCommentRepository blogPostCommentRepository;

    @PostMapping("/posts/{id}/comments")
    public BlogPostComment registerComments(@PathVariable Long id,
        @RequestBody BlogPostCommentRequest request) {

        BlogPostEntity postEntity = blogPostRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        BlogPostCommentEntity entity = blogPostCommentRepository.save(
            BlogPostCommentEntity.builder()
                .content(request.getContent())
                .blogPost(postEntity)
                .build());

        return BlogPostComment.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .build();
    }
}
