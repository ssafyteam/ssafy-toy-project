package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
