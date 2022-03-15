package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.UserAssetApplicationException;
import server.admin.model.common.rest.RestFailResponse;

@RestControllerAdvice
public class UserAssetApplicationHandler {
    @Getter
    @RequiredArgsConstructor
    public enum UserAssetApplicationErrorCode{
        USER_ASSET_APPLICATION_NOT_EXIST_CODE(404);

//        private String description;
        private final int statusCode;
    }

    @ExceptionHandler(UserAssetApplicationException.UserAssetApplicationNotExistException.class)
    public RestFailResponse userAssetApplicationNotExistExceptionHandler(UserAssetApplicationException.UserAssetApplicationNotExistException exception){
        return new RestFailResponse<>(UserAssetApplicationErrorCode.USER_ASSET_APPLICATION_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
