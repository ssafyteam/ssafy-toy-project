package com.weareone.quicklog.exception;

public class GptCannotMakeNextLineException extends RuntimeException {
    public GptCannotMakeNextLineException() {
        super("추천할 다음 문장이 없습니다");
    }

}
