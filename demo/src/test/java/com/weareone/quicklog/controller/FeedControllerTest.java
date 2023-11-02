package com.weareone.quicklog.controller;

import com.weareone.quicklog.dto.response.FeedInfoResponse;
import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.mapper.FeedMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebMvcTest(FeedController.class)
class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedMapper mapper;

    @Test
    public void EntityDto매핑테스트() {
        // given
        User user = new User("email", "password", LocalDate.now(), "name", "nickname");
        Image image = new Image(1L, null, "name", "url", "originName");
        Post post = new Post(user, "title", "category", "contents", false);
        post.addImage(image);
        image.setPost(post);

        FeedInfoResponse result = FeedInfoResponse.builder()
                .id(post.getId()).title(post.getTitle())
                .contents(post.getContents()).category(post.getCategory())
                        .user(mapper.userToUserDtoResponse(user)).images(mapper.imageDatasToImageResponses(
                                mapper.imagesToImageDatas(post.getImages()))).build();

        // when
        when(mapper.postToFeedInfoResponse(post)).thenReturn(result);
        // GET 요청 수행
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/feed"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println("처리 중 오류 발생");
            e.printStackTrace();
        }

        // then
    }
}