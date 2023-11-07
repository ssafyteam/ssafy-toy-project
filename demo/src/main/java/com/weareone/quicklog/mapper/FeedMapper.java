package com.weareone.quicklog.mapper;

import com.weareone.quicklog.dto.image.ImageData;
import com.weareone.quicklog.dto.image.ImageResponse;
import com.weareone.quicklog.dto.response.FeedInfoResponse;
import com.weareone.quicklog.dto.response.UserDtoResponse;
import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedMapper {
    @Mapping(source = "category.categoryName", target = "category")
    FeedInfoResponse postToFeedInfoResponse(Post post);
    default Page<FeedInfoResponse> pagePostToPageFeedInfoResponse(Page<Post> page) {
        return page.map(this::postToFeedInfoResponse);
    }

    UserDtoResponse userToUserDtoResponse(User user);

    ImageData imageToImageData(Image image);

    List<ImageData> imagesToImageDatas(List<Image> images);

}
