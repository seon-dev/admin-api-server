package server.admin.asset.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.asset.exception.AssetPrototypeException;
import server.admin.model.asset.exception.AssetSeasonException;

import java.time.LocalDateTime;

public class AssetSeasonHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AssetErrorCode {
        ASSET_SEASON_NOT_EXIST_CODE("asset_season_404", "해당하는 에셋 시즌 라인이 존재하지 않습니다."),
        ASSET_SEASON_DUPLICATED_CODE("asset_season_duplicated", "해당하는 에셋 시즌이 이미 존재합니다.");
        private final String statusCode;
        private final String description;
    }

    @ExceptionHandler(AssetSeasonException.AssetSeasonNotExistException.class)
    public ErrorResponse assetSeasonNotExistExceptionHandler(AssetSeasonException.AssetSeasonNotExistException exception){
        return new ErrorResponse(LocalDateTime.now(), AssetErrorCode.ASSET_SEASON_NOT_EXIST_CODE.getStatusCode(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
