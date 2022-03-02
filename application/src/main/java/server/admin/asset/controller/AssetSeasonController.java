package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetLineCreateRequest;
import server.admin.model.asset.dto.request.AssetLineUpdateRequest;
import server.admin.model.asset.dto.request.AssetSeasonCreateRequest;
import server.admin.model.asset.dto.request.AssetSeasonUpdateRequest;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.dto.response.AssetSeasonResponse;
import server.admin.model.asset.entity.AssetSeason;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetSeasonService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/asset-season")
@RequiredArgsConstructor
public class AssetSeasonController {
    private final AssetSeasonService assetSeasonService;

    @GetMapping("/{assetSeasonId}")
    @ApiOperation(value = "단일 에셋 라인 조회",notes = "해당 에셋 라인을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetSeasonResponse> getAssetSeason(
            @PathVariable("assetSeasonId") Long assetSeasonId
    ){
        return RestSuccessResponse.newInstance(
                assetSeasonService.getAssetSeason(assetSeasonId)
        );
    }

    @GetMapping
    @ApiOperation(value = "전체 에셋 라인 조회", notes = "전체 에셋 라인을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetSeasonResponse>> getAllAssetSeason(

    ){
        return RestSuccessResponse.newInstance(
                assetSeasonService.getAllAssetSeason()
        );
    }

    @PostMapping
    @ApiOperation(value="에셋 라인 생성", notes = "에셋 라인을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetSeasonResponse> createAssetSeason(
            @RequestBody @Valid AssetSeasonCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetSeasonService.createAssetSeason(request)
        );
    }

    @PatchMapping("/{assetSeasonId}")
    @ApiOperation(value= "에셋 라인 업데이트", notes = "해당 에셋 라인을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetSeasonResponse> updateAssetLine(
            @RequestBody @Valid AssetSeasonUpdateRequest request,
            @PathVariable("assetSeasonId") Long assetSeasonId
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetSeasonService.updateAssetSeason(assetSeasonId, request)
        );
    }

    @DeleteMapping("/{assetSeasonId}")
    @ApiOperation(value = "에셋 라인 삭제", notes = "해당 에셋 라인을 비활성화합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetSeason(
            @PathVariable("assetSeasonId") Long id
    ){
        assetSeasonService.deleteAssetSeason(id);
    }
}
