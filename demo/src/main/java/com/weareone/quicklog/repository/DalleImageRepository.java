package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.DalleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DalleImageRepository extends JpaRepository<DalleImage, Long> {

    Optional<DalleImage> findByMember(String memberId);
}
