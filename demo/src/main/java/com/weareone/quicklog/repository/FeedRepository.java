package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
