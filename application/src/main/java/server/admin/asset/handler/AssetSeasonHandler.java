package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;


import static server.admin.model.asset.exception.AssetSeasonException.*;
@RestControllerAdvice
public class AssetSeasonHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetErrorCode {
        ASSET_SEASON_NOT_EXIST_CODE(404, "해당하는 에셋 시즌이 존재하지 않습니다."),
        ASSET_SEASON_DUPLICATED_CODE(409, "해당하는 에셋 시즌이 이미 존재합니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetSeasonNotExistException.class)
    public RestFailResponse assetSeasonNotExistExceptionHandler(AssetSeasonNotExistException exception){
        return new RestFailResponse<>(AssetErrorCode.ASSET_SEASON_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }

}
