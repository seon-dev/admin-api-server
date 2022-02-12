package server.admin.user.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.user.exception.UserException;

public class UserHandler {
    @Getter
    @RequiredArgsConstructor
    public enum UserErrorCode{
        USER_NOT_EXIST_CODE("user_404", "해딩하는 유저가 존재하지 않습니다."),
        USER_DUPLICATED_CODE("user_duplicated_409", "해당하는 유저가 이미 존재합니다.");

        private final String statusCode;
        private final String description;
    }

    @ExceptionHandler(UserException.UserNotExistException.class)
    public ErrorResponse UserNotExistHandler(UserException.UserNotExistException exception){
        return new ErrorResponse(UserErrorCode.USER_NOT_EXIST_CODE.getStatusCode(), exception.getMessage(), HttpStatus.NOT_FOUND);
    }



}
