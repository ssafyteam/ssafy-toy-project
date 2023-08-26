package skt.weareone.mvp.exception;

public class GptCannotMakeNextLineException extends RuntimeException {
    public GptCannotMakeNextLineException() {
        super("추천할 다음 문장이 없습니다");
    }

}
