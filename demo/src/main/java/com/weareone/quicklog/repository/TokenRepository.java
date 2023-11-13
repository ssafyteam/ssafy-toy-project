package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByRefreshToken(String token);
    @Transactional
    Long deleteByRefreshToken(String token);
}
