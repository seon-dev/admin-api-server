package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.AssetCategoryException;
import server.admin.model.asset.exception.AssetLineException;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.common.rest.RestResponse;

@RestControllerAdvice
public class AssetCategoryHandler {

    @Getter
    @RequiredArgsConstructor
    public enum AssetCategoryErrorCode{
        ASSET_CATEGORY_NOT_EXIST_CODE(404, "asset_brand_category_404 - 해당하는 에셋 카테고리가 존재하지 않습니다."),
        ASSET_CATEGORY_DUPLICATE_CODE(409, "해당하는 에셋 카테고리가 이미 존재합니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetCategoryException.AssetCategoryNotExistException.class)
    public RestFailResponse assetCategoryNotExistHandler(AssetCategoryException.AssetCategoryNotExistException exception){
        return new RestFailResponse(AssetCategoryErrorCode.ASSET_CATEGORY_NOT_EXIST_CODE.statusCode, exception.getMessage());
    }

    @ExceptionHandler(AssetCategoryException.AssetCategoryDuplicateException.class)
    public RestFailResponse assetCategoryDuplicateHandler(AssetCategoryException.AssetCategoryDuplicateException exception){
        return new RestFailResponse(AssetCategoryErrorCode.ASSET_CATEGORY_DUPLICATE_CODE.statusCode, exception.getMessage());
    }
}
