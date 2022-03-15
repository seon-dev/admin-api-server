package server.admin.brand.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;

import static server.admin.model.brand.exception.BrandException.*;
@RestControllerAdvice
public class BrandHandler {
    @Getter
    @RequiredArgsConstructor
    public enum BrandErrorCode{
        BRAND_NOT_EXIST_CODE(404, "해당하는 브랜드가 존재하지 않습니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(BrandNotExistException.class)
    public RestFailResponse brandNotExistHandler(BrandNotExistException exception){
        return new RestFailResponse<>(BrandErrorCode.BRAND_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
