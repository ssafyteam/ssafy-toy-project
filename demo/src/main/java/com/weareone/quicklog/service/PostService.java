package com.weareone.quicklog.service;

import com.weareone.quicklog.dto.request.BlogPostRequest;
import com.weareone.quicklog.dto.request.UpdatePostRequest;

public interface PostService {
    Long save(BlogPostRequest request);

    Long update(long postId, UpdatePostRequest request);

    void delete(long postId);
}
