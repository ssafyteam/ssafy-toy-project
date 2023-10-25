package com.weareone.quicklog.exception;

public class DalleCannotMakeImageException extends RuntimeException {
    public DalleCannotMakeImageException() {
        super("이미지를 추천할 수 없습니다.");
    }

}
