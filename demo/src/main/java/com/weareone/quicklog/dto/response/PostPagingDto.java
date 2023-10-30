package com.weareone.quicklog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostPagingDto<T> {
    private T data;
    private PageInfo pageInfo;
}