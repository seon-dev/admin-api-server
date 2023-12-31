package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.AssetLineException;
import server.admin.model.common.rest.RestFailResponse;

@RestControllerAdvice
public class AssetLineHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetLineErrorCode{
        ASSET_LINE_NOT_EXIST_CODE(404, "해당하는 에셋 라인이 존재하지 않습니다."),
        ASSET_LINE_DUPLICATE_CODE(409, "해당 에셋 라인이 이미 존재합니다.");
        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetLineException.AssetLineNotExistException.class)
    public RestFailResponse assetLineNotExistHandler(AssetLineException.AssetLineNotExistException exception){
        return new RestFailResponse(AssetLineErrorCode.ASSET_LINE_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }

    @ExceptionHandler(AssetLineException.AssetLineDuplicateException.class)
    public RestFailResponse assetLineDuplicateHandler(AssetLineException.AssetLineDuplicateException exception){
        return new RestFailResponse(AssetLineErrorCode.ASSET_LINE_DUPLICATE_CODE.getStatusCode(), exception.getMessage());
    }
}
