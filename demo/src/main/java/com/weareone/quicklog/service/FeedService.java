package com.weareone.quicklog.service;

import com.weareone.quicklog.dto.response.FeedInfoResponse;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedService {
    Page<FeedInfoResponse> getFeedList(Pageable pageable, String keyword);
    Page<FeedInfoResponse> findAll(Pageable pageable);
    Page<FeedInfoResponse> findByUser(Pageable pageable, String email);

    Page<FeedInfoResponse> searchByTitle(Pageable pageable, String keyword);
}
