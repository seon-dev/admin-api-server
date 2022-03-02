package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetQualityCreateRequest;
import server.admin.model.asset.dto.request.AssetQualityUpdateRequest;
import server.admin.model.asset.dto.response.AssetQualityResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetQualityService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/asset-quality")
@RequiredArgsConstructor
public class AssetQualityController {
    private final AssetQualityService assetQualityService;

    @GetMapping("/{assetQualityId}")
    @ApiOperation(notes = "단일 에셋 퀄리티 조회", value = "해당 에셋 퀄리티를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetQualityResponse> getAssetQuality(
            @PathVariable("assetQualityId") Long id
    ){
        return RestSuccessResponse.newInstance(
          assetQualityService.getAssetQuality(id)
        );
    }

    @GetMapping
    @ApiOperation(notes = "전체 에셋 퀄리티 조회", value = "전체 에셋 퀄리티를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetQualityResponse>> getAllAssetQuality(

    ){
        return RestSuccessResponse.newInstance(
                assetQualityService.getAllAssetQuality()
        );
    }

    @PostMapping
    @ApiOperation(notes = "에셋 퀄리티 생성", value = "에셋 퀄리티를 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetQualityResponse> createAssetQuality(
            @RequestBody @Valid AssetQualityCreateRequest request
    ){
        return RestSuccessResponse.newInstance(
                assetQualityService.createAssetQuality(request)
        );
    }

    @PatchMapping("/{assetQualityId}")
    @ApiOperation(notes = "에셋 퀄리티 업데이트", value = "해당 에셋 퀄리티를 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetQualityResponse> updateAssetQuality(
            @PathVariable("assetQualityId") Long id,
            @RequestBody @Valid AssetQualityUpdateRequest request
            ){
        return RestSuccessResponse.newInstance(
            assetQualityService.updateAssetQuality(id , request)
        );
    }
}
