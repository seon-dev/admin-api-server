package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.AssetQualityException;
import server.admin.model.common.rest.RestFailResponse;
@RestControllerAdvice
public class AssetQualityHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetQualityErrorCode{
        ASSET_QUALITY_NOT_EXIST_CODE(404, "asset_brand_category_404 - 해당하는 에셋 등급이 존재하지 않습니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetQualityException.AssetQualityNotExistException.class)
    public RestFailResponse assetQualityNotExistHandler(AssetQualityException.AssetQualityNotExistException exception){
        return new RestFailResponse(AssetQualityErrorCode.ASSET_QUALITY_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }


}
