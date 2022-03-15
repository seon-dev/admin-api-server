package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.AssetCollectionException;
import server.admin.model.common.rest.RestFailResponse;

@RestControllerAdvice
public class AssetCollectionHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetCollectionErrorCode{
        ASSET_COLLECTION_NOT_EXIST_CODE(404, "해당하는 에셋 컬렉션이 존재하지 않습니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetCollectionException.AssetCollectionNotExistException.class)
    public RestFailResponse assetCollectionNotExistHandler(AssetCollectionException.AssetCollectionNotExistException exception){
        return new RestFailResponse(AssetCollectionErrorCode.ASSET_COLLECTION_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
