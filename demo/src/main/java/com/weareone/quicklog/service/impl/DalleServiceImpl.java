package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.config.ChatGptConfig;
import com.weareone.quicklog.config.DalleConfig;
import com.weareone.quicklog.dto.image.ImageFormat;
import com.weareone.quicklog.dto.image.ImageRequest;
import com.weareone.quicklog.dto.image.ImageResponse;
import com.weareone.quicklog.dto.image.ImageSize;
import com.weareone.quicklog.exception.DalleCannotMakeImageException;
import com.weareone.quicklog.service.DalleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DalleServiceImpl implements DalleService {

    @Value("${gpt-apiKey}")
    private String gpt_apiKey;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ImageRequest> createHttpEntity(ImageRequest imageRequest) {
        HttpHeaders headers = new HttpHeaders(); // 헤더 설정
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8")); // Content-type JSON으로 설정
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + gpt_apiKey); // Header에 인증 방식 설정
        return new HttpEntity<>(imageRequest, headers);
    }

    public ImageResponse getResponse(HttpEntity<ImageRequest> imageRequest) {

        ResponseEntity<ImageResponse> responseEntity = restTemplate.postForEntity(
                DalleConfig.url,
                imageRequest,
                ImageResponse.class);
        if (responseEntity.getStatusCode().value()!=HttpStatus.OK.value()) {
            throw new DalleCannotMakeImageException();
        } else {
            log.info("생성한 이미지 : {}", responseEntity);
        }
        return responseEntity.getBody();
    }


    @Override
    public String imageGenerate(String prompt) {
        ImageRequest imageRequest = new ImageRequest(prompt, null, null, null, null);
        ImageResponse imageResponse = this.getResponse(this.createHttpEntity(imageRequest));
        String url = "";
        try {
            url = imageResponse.getData().get(0).getUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public List<String> imageGenerate(String prompt, int n, ImageSize size, ImageFormat format) {
        ImageRequest imageRequest = new ImageRequest(prompt, n, size.getSize(), format.getFormat(), null);
        ImageResponse imageResponse = this.getResponse(this.createHttpEntity(imageRequest));
        List<String> list = new ArrayList<>();
        try {

            imageResponse.getData().forEach(imageData -> {
                if (format.equals(ImageFormat.URL)) {
                    list.add(imageData.getUrl());
                } else {
                    list.add(imageData.getB64Json());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ImageResponse imageGenerateRequest(ImageRequest imageRequest) {
        return this.getResponse(this.createHttpEntity(imageRequest));
    }

}
