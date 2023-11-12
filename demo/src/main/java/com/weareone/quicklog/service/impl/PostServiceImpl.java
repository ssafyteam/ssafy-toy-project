package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.request.BlogPostRequest;
import com.weareone.quicklog.entity.*;
import com.weareone.quicklog.dto.request.UpdatePostRequest;
import com.weareone.quicklog.exception.PostNotFoundException;
import com.weareone.quicklog.repository.*;
import com.weareone.quicklog.repository.file.FilePath;
import com.weareone.quicklog.service.PostService;
import com.weareone.quicklog.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final CategoryRepository categoryRepository;
    private final S3Uploader s3Uploader;
    public Long createPost(BlogPostRequest request, List<MultipartFile> images) throws IOException {
        Category category = new Category(request.getCategory());
        //유저 연관관계 매핑
        category.addUser(null);
        categoryRepository.save(category);
        Post post = new Post(null, request.getTitle(), category, request.getContents(), request.isPublic());
        if (images!=null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                Image img = s3Uploader.upload(image,"static");
//                String storeFileName = createStoreFileName(image.getOriginalFilename());
//                image.transferTo(new File(FilePath.filePath + storeFileName));
//                Image img = new Image();
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
    public Long update(long postId, UpdatePostRequest request, List<MultipartFile> images) throws IOException {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());

        //원래 포스트의 이미지 받아와서 삭제
        List<Image> findPostImages = findPost.getImages();
        for (Image img : findPostImages) {
            imageRepository.delete(img);

        }
        //원래 포스트의 태그들 받아와서 삭제
        List<PostTag> postTags = findPost.getPostTags();
        for (PostTag postTag : postTags) {
            postTagRepository.delete(postTag); // 이걸로 되는지 확인
        }

        Category category = new Category(request.getCategory());  
        findPost.changePostInfo(category, request.getTitle(), request.getContents(), request.isPublic());
        if (!images.isEmpty()) { //null 처리도 해야되나?
            for (MultipartFile image : images) {
                String storeFileName = null;
//                storeFileName = createStoreFileName(image.getOriginalFilename());
//                image.transferTo(new File(FilePath.filePath + storeFileName));
//                String fileUrl = ""
//                Image img = new Image(storeFileName,fileUrl,image.getOriginalFilename());
//                img.setPost(findPost);
//                imageRepository.save(img);
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
    public void delete(long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        postRepository.delete(findPost);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}