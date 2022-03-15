package server.admin.user.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;

import static server.admin.model.user.exception.UserException.*;

@RestControllerAdvice
public class UserHandler {
    @Getter
    @RequiredArgsConstructor
    public enum UserErrorCode{
        USER_NOT_EXIST_CODE(404, "해딩하는 유저가 존재하지 않습니다."),
        USER_DUPLICATED_CODE(409, "해당하는 유저가 이미 존재합니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(UserNotExistException.class)
    public RestFailResponse userNotExistHandler(UserNotExistException exception){
        return new RestFailResponse<>(UserErrorCode.USER_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }



}
