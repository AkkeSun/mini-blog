package com.sweettracker.resourceserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostCommentRepository extends JpaRepository<BlogPostCommentEntity, Long> {

    void deleteAllByBlogPost(BlogPostEntity blogPost);
}
