package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag,Long> {
}
