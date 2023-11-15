package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.PostInfo;
import com.weareone.quicklog.dto.request.BlogPostRequest;
import com.weareone.quicklog.entity.*;
import com.weareone.quicklog.dto.request.UpdatePostRequest;
import com.weareone.quicklog.exception.PostNotFoundException;
import com.weareone.quicklog.exception.UserNotFoundException;
import com.weareone.quicklog.repository.*;
import com.weareone.quicklog.service.PostService;
import com.weareone.quicklog.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final CategoryRepository categoryRepository;
    private final S3Uploader s3Uploader;
    public Long createPost(BlogPostRequest request, List<MultipartFile> images, String email) throws IOException {
        Category category = new Category(request.getCategory());
        User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException());
        //유저 연관관계 매핑
        category.addUser(user);
        categoryRepository.save(category);
        Post post = new Post(user, request.getTitle(), category, request.getContents(), request.isPublic());
        if (images!=null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                Image img = s3Uploader.upload(image,"static");
                img.setPost(post);
                imageRepository.save(img);
            }
        }

        if (request.getTags() != null) {
            String[] tags = request.getTags();
            for (String tag : tags) {
                Tag nowTag = new Tag(tag);
                tagRepository.save(nowTag);
                PostTag postTag = new PostTag();
                postTag.addPostAndTag(post, nowTag);
                postTagRepository.save(postTag);
            }
        }
        return postRepository.save(post).getId();
    }

    @Override
    public PostInfo readPost(long id, String email) {
        userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());

        PostInfo postInfo = new PostInfo(post.getUser().getEmail(), post.getUser().getNickname(), post.getTitle(), post.getCategory().getCategoryName(), post.getCreatedAt(), post.getContents(), post.isPublic(), post.getImages(), post.getPostTags());
        return postInfo;
    }

    @Override
    public Long update(long postId, UpdatePostRequest request, List<MultipartFile> images, String email) throws IOException {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException());

        //원래 포스트의 이미지 받아와서 삭제
        List<Image> findPostImages = findPost.getImages();
        for (Image img : findPostImages) {
            imageRepository.delete(img);
            s3Uploader.deleteFile(img.getImageName());
        }
        //원래 포스트의 태그들 받아와서 삭제
        List<PostTag> postTags = findPost.getPostTags();
        for (PostTag postTag : postTags) {
            postTagRepository.delete(postTag); // 이걸로 되는지 확인
        }

        Category category = new Category(request.getCategory());

        category.addUser(user);
        categoryRepository.save(category);

        findPost.changePostInfo(category, request.getTitle(), request.getContents(), request.isPublic());

        if (images!=null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                Image img = s3Uploader.upload(image,"static");
                img.setPost(findPost);
                imageRepository.save(img);
            }
        }
        if (request.getTags() != null) {
            String[] tags = request.getTags();
            for (String tag : tags) {
                Tag nowTag = new Tag(tag);
                tagRepository.save(nowTag);
                PostTag postTag = new PostTag();
                postTag.addPostAndTag(findPost,nowTag);
                postTagRepository.save(postTag);
            }
        }
        return findPost.getId();
    }

    @Override
    public void delete(long postId, String email) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        List<Image> findPostImages = findPost.getImages();
        for (int i = 0; i < findPostImages.size(); i++) {
            String s = findPostImages.get(i).getImageName();
            System.out.println(s);
            s3Uploader.deleteFile(s);
        }
        postRepository.delete(findPost);
    }



}