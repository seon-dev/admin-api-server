package server.admin.common;

import lombok.Getter;

@Getter
public class BasicMessage {
    private String message;

    public BasicMessage(String message) {
        this.message = message;
    }//메세지 출력하는법 구글링하기

}
