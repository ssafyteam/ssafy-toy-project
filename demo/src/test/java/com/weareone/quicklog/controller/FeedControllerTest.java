package com.weareone.quicklog.controller;

import com.weareone.quicklog.dto.response.FeedInfoResponse;
import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.mapper.FeedMapper;
import com.weareone.quicklog.service.FeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FeedControllerTest {

    @InjectMocks
    private FeedController feedController;

    @Mock
    private FeedService feedService;

    @Mock
    private FeedMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void EntityDto매핑테스트() {
        // given
        User user = new User(1L, "email", "password", LocalDate.now(), "name", "nickname", null, new ArrayList<>());
        Image image = new Image("name", "url", "originName");
        Post post = new Post(user, "title", null, "contents", false);
        post.addImage(image);
        image.setPost(post);


        Mockito.when(mapper.postToFeedInfoResponse(any(Post.class))).thenReturn(new FeedInfoResponse());

        // 메서드 실행
        ResponseEntity responseEntity = feedController.mapperTest(post);

        // 결과 확인
        verify(mapper).postToFeedInfoResponse(post);
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);

//        FeedInfoResponse result = FeedInfoResponse.builder()
//                .id(post.getId()).title(post.getTitle())
//                .contents(post.getContents())
//                .user(mapper.userToUserDtoResponse(user)).images(mapper.imageDatasToImageResponses(
//                                mapper.imagesToImageDatas(post.getImages()))).build();
//
//        // when
//        when(mapper.postToFeedInfoResponse(post)).thenReturn(result);
//        // GET 요청 수행
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/feed/all"))
//                    .andExpect(MockMvcResultMatchers.status().isOk());
//        } catch (Exception e) {
//            System.out.println("처리 중 오류 발생");
//            e.printStackTrace();
//        }

        // then
    }
}