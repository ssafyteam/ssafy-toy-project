package com.weareone.quicklog.service;

import com.weareone.quicklog.dto.image.ImageFormat;
import com.weareone.quicklog.dto.image.ImageRequest;
import com.weareone.quicklog.dto.image.ImageResponse;
import com.weareone.quicklog.dto.image.ImageSize;

import java.util.List;
public interface DalleService {
    String imageGenerate(String prompt);
    List<String> imageGenerate(String prompt, int n, ImageSize size, ImageFormat format);
    ImageResponse imageGenerateRequest(ImageRequest imageRequest);
}
