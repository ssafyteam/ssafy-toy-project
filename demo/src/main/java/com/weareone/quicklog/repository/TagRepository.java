package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
