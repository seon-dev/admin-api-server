package server.admin.permission.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.permission.exception.PermissionException;

@RestControllerAdvice
public class PermissionHandler {
    @Getter
    @RequiredArgsConstructor
    public enum PermissionErrorCode{
        PERMISSION_NOT_EXIST_CODE(404, "해당하는 권한이 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(PermissionException.PermissionNotExistException.class)
    public RestFailResponse permissionNotExistException(PermissionException.PermissionNotExistException exception){
        return new RestFailResponse<>(PermissionErrorCode.PERMISSION_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());

    }
}
