package com.sweettracker.resourceserver.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {

    @EntityGraph(attributePaths = {"comments"})
    Optional<BlogPostEntity> findById(Long id);

    @Query("SELECT COUNT(p) FROM BlogPostEntity p")
    int countAll();

    @Query("SELECT p FROM BlogPostEntity p order by p.id desc")
    List<BlogPostEntity> findAllPosts(PageRequest pageable);
}
