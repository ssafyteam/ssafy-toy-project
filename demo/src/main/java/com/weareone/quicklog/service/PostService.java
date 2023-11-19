package com.weareone.quicklog.service;

import com.weareone.quicklog.dto.PostInfo;
import com.weareone.quicklog.dto.request.BlogPostRequest;
import com.weareone.quicklog.dto.request.UpdatePostRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Long createPost(BlogPostRequest request, List<MultipartFile> images, String email) throws IOException;
    Long update(long postId, UpdatePostRequest request, List<MultipartFile> images, String email) throws IOException;
    void delete(long postId, String email);

    PostInfo readPost(long id, String email);
}
