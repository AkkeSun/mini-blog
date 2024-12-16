package com.sweettracker.resourceserver.controller;

import com.sweettracker.resourceserver.dao.BlogPostCommentRepository;
import com.sweettracker.resourceserver.dao.BlogPostEntity;
import com.sweettracker.resourceserver.dao.BlogPostRepository;
import com.sweettracker.resourceserver.domain.BlogPost;
import com.sweettracker.resourceserver.domain.BlogPostList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class BlogPostController {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostCommentRepository blogPostCommentRepository;

    @GetMapping("/posts")
    public BlogPostList findAll(
        @RequestParam(defaultValue = "0") int page) {
        log.info("findALl call - {}", page);

        int pageSize = 5;
        int totalPosts = blogPostRepository.countAll();
        int totalPage =
            totalPosts % pageSize == 0 ? totalPosts / pageSize - 1 : totalPosts / pageSize;

        return BlogPostList.builder()
            .nowPage(page)
            .totalPage(totalPage)
            .posts(blogPostRepository.findAllPosts(PageRequest.of(page, pageSize)).stream()
                .map(BlogPostEntity::toListItemDomain)
                .toList())
            .build();
    }

    @GetMapping("/posts/{id}")
    public BlogPost findOne(@PathVariable Long id) {
        log.info("findOne call - {}", id);
        BlogPostEntity entity = blogPostRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return entity.toDomain();
    }

    @Transactional
    @DeleteMapping("/posts/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.info("delete call - {}", id);
        blogPostRepository.findById(id).ifPresent(blogPost -> {
            blogPostCommentRepository.deleteAllByBlogPost(blogPost);
            blogPostRepository.delete(blogPost);
        });
        return true;
    }

    @PutMapping("/posts/{id}")
    public BlogPost update(@PathVariable Long id,
        @RequestBody BlogPostRequest request) {
        log.info("update call - {}", id);
        BlogPostEntity blogPost = blogPostRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        blogPost.update(request);
        blogPostRepository.save(blogPost);

        return blogPost.toDomain();
    }

    @PostMapping("/posts")
    public BlogPost register(@RequestBody BlogPostRequest request) {
        BlogPostEntity entity = blogPostRepository.save(request.toEntity());
        return BlogPost.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .build();
    }
}
