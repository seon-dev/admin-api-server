package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.asset.exception.AssetPrototypeException;
import server.admin.model.common.rest.RestFailResponse;

@RestControllerAdvice
public class AssetPrototypeHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetPrototypeErrorCode {
        ASSET_PROTOTYPE_NOT_EXIST_CODE(404, "해당하는 에셋 에셋프로토타입이 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetPrototypeException.AssetPrototypeNotExistException.class)
    public RestFailResponse assetPrototypeNotExistHandler(AssetPrototypeException.AssetPrototypeNotExistException exception){
        return new RestFailResponse(AssetPrototypeErrorCode.ASSET_PROTOTYPE_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
