package com.weareone.quicklog.repository;

import com.weareone.quicklog.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    boolean existsByRefreshToken(String token);
}
