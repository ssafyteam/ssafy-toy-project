package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.mapper.FeedMapper;
import com.weareone.quicklog.repository.FeedRepository;
import com.weareone.quicklog.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final FeedMapper mapper;

    @Override
    public Page<FeedInfoResponse> findAll(Pageable pageable) {
        Page<Post> posts = feedRepository.findAll(pageable);
        return mapper.pagePostToPageFeedInfoResponse(posts);
    }

    @Override
    public Page<FeedInfoResponse> findByUser(Pageable pageable, String email) {
        // TODO : 유저 찾을 수 없을 때 Exception 변경
        User registUser = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException());
        Page<Post> posts = feedRepository.findByUser(registUser, pageable);
        return mapper.pagePostToPageFeedInfoResponse(posts);
    }

    @Override
    public Page<FeedInfoResponse> searchByTitle(Pageable pageable, String keyword) {
        Page<Post> posts = feedRepository.findByTitleContaining(keyword, pageable);
        return mapper.pagePostToPageFeedInfoResponse(posts);
    }


}
