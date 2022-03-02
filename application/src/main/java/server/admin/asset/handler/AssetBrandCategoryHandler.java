package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.asset.exception.AssetBrandCategoryException;
import server.admin.model.asset.exception.AssetCollectionException;
import server.admin.model.common.rest.RestFailResponse;

import java.time.LocalDateTime;
@RestControllerAdvice
public class AssetBrandCategoryHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetBrandCategoryErrorCode{
        ASSET_BRAND_CATEGORY_NOT_EXIST_CODE(404, "asset_brand_category_404 - 해당하는 에셋 브랜드 카테고리가 존재하지 않습니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetBrandCategoryException.AssetBrandCategoryNotExistException.class)
    public RestFailResponse assetBrandCategoryNotExistHandler(AssetBrandCategoryException.AssetBrandCategoryNotExistException exception){
        return new RestFailResponse(AssetBrandCategoryErrorCode.ASSET_BRAND_CATEGORY_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
