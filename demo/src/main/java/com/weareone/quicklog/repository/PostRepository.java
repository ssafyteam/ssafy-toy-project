package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
