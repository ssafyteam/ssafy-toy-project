package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
