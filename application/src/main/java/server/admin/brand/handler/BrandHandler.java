package server.admin.brand.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import server.admin.asset.handler.AssetLineHandler;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.asset.exception.AssetLineException;
import server.admin.model.brand.exception.BrandException;

import java.time.LocalDateTime;

import static server.admin.model.brand.exception.BrandException.*;

public class BrandHandler {
    @Getter
    @RequiredArgsConstructor
    public enum BrandErrorCode{
        BRAND_NOT_EXIST_CODE(404, "해당하는 브랜드가 존재하지 않습니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(BrandNotExistException.class)
    public ErrorResponse brandNotExistHandler(BrandNotExistException exception){
        return new ErrorResponse(BrandErrorCode.BRAND_NOT_EXIST_CODE.getStatusCode(), exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
