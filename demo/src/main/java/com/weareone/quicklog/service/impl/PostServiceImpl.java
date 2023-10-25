package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.ImageDTO;
import com.weareone.quicklog.dto.request.BlogPostRequest;
import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.dto.request.UpdatePostRequest;
import com.weareone.quicklog.entity.Tag;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.exception.PostNotFoundException;
import com.weareone.quicklog.repository.PostRepository;
import com.weareone.quicklog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public Long save(BlogPostRequest request) {
        Post post = new Post(null, request.getTitle(), request.getCategory(), request.getContents(), request.isPublic());

        if (request.getImages() != null) {
            Image[] images = request.getImages();
            for (Image image : images) {
                post.addImage(image);
            }
        }
//        if (request.getTags() != null) {
//            String[] tags = request.getTags();
//            for (Tag tag : tags) {
//                post.addTag();
//            }
//        }
        return postRepository.save(post).getId();
    }

    @Override
    public Long update(long postId, UpdatePostRequest request) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        findPost.changePostInfo(null, null, null, false);
        if (request.getImages() != null) {
            Image[] images = request.getImages();
            for (Image image : images) {
                findPost.addImage(image);
            }
        }
        return findPost.getId();
    }

    @Override
    public void delete(long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        postRepository.delete(findPost);
    }

}