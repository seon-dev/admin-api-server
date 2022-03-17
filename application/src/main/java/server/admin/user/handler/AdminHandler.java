package server.admin.user.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.user.exception.AdminException;

@RestControllerAdvice
public class AdminHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AdminErrorCode{
        ADMIN_NOT_EXIST_CODE(404, "해당 어드민이 존재하지 않습니다."),
        ADMIN_DUPLICATE_CODE(409, "해당 어드민이 이미 존재합니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AdminException.AdminNotExistException.class)
    public RestFailResponse AdminNotExistHandler(AdminException.AdminNotExistException exception){
        return new RestFailResponse(AdminErrorCode.ADMIN_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }

    @ExceptionHandler(AdminException.DuplicateAdminException.class)
    public RestFailResponse DuplicateAdminHandler(AdminException.DuplicateAdminException exception){
        return new RestFailResponse(AdminErrorCode.ADMIN_DUPLICATE_CODE.getStatusCode(), exception.getMessage());
    }

}
