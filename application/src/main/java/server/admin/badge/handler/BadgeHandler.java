package server.admin.badge.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.badge.exception.BadgeException;

import java.time.LocalDateTime;

import static server.admin.model.badge.exception.BadgeException.*;

public class BadgeHandler {
    @Getter
    @RequiredArgsConstructor
    public enum BadgeErrorCode{
        BADGE_NOT_EXIST_CODE("badge_404", "해당하는 뱃지가 존재하지 않습니다.");

        private final String statusCode;
        private final String description;
    }

    @ExceptionHandler(BadgeNotExistException.class)
    public ErrorResponse BadgeNotExistHandler(BadgeNotExistException exception){
        return new ErrorResponse(BadgeErrorCode.BADGE_NOT_EXIST_CODE.getStatusCode(),exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
