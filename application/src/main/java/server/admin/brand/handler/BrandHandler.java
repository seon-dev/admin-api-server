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

public class BrandHandler {
    @Getter
    @RequiredArgsConstructor
    public enum BrandErrorCode{
        BRAND_NOT_EXIST_CODE("brand_404", "해당하는 에셋 라인이 존재하지 않습니다.");
        private final String statusCode;
        private final String description;
    }

    @ExceptionHandler(BrandException.BrandNotExistException.class)
    public ErrorResponse BrandNotExistHandler(BrandException.BrandNotExistException exception){
        return new ErrorResponse(LocalDateTime.now(), BrandErrorCode.BRAND_NOT_EXIST_CODE.getStatusCode(), exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
