package server.admin.styling.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.styling.exception.UserStylingException;

@RestControllerAdvice
public class UserStylingHandler {
    @Getter
    @RequiredArgsConstructor
    public enum UserStylingErrorCode{
        USER_STYLING_NOT_EXIST_CODE(404);

        private final int statusCode;
    }

    @ExceptionHandler(UserStylingException.UserStylingNotExistException.class)
    public RestFailResponse userStylingNotExistException(UserStylingException.UserStylingNotExistException exception){
        return new RestFailResponse<>(UserStylingErrorCode.USER_STYLING_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
