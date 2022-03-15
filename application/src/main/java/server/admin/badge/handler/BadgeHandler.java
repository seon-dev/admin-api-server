package server.admin.badge.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;

import static server.admin.model.badge.exception.BadgeException.*;
@RestControllerAdvice
public class BadgeHandler {
    @Getter
    @RequiredArgsConstructor
    public enum BadgeErrorCode{
        BADGE_NOT_EXIST_CODE(404, "해당하는 뱃지가 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(BadgeNotExistException.class)
    public RestFailResponse badgeNotExistHandler(BadgeNotExistException exception){
        return new RestFailResponse<>(BadgeErrorCode.BADGE_NOT_EXIST_CODE.getStatusCode(),exception.getMessage());
    }
}
