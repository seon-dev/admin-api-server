package server.admin.event.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.event.exception.AppEventException;
import server.admin.user.handler.UserHandler;

@RestControllerAdvice
public class AppEventHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AppEventErrorCode{
        APP_EVENT_NOT_EXIST_CODE(404, "해당하는 이벤트가 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AppEventException.AppEventNotExistException.class)
    public RestFailResponse appEventNotExistHandler(AppEventException.AppEventNotExistException exception){
        return new RestFailResponse<>(AppEventErrorCode.APP_EVENT_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
