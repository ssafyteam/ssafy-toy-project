package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.repository.FeedRepository;
import com.weareone.quicklog.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.weareone.quicklog.dto.response.FeedInfoResponse;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public Page<FeedInfoResponse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<FeedInfoResponse> findByUser(Pageable pageable, String email) {
        return null;
    }

    @Override
    public Page<FeedInfoResponse> searchByTitle(String keyword) {
        return null;
    }


}
