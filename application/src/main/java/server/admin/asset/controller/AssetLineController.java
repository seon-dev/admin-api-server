package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetLineCreateRequest;
import server.admin.model.asset.dto.request.AssetLineUpdateRequest;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetLineService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/asset-line")
@RequiredArgsConstructor
public class AssetLineController {
    private final AssetLineService assetLineService;

    @GetMapping("/{assetLineId}")
    @ApiOperation(value = "단일 에셋 라인 조회",notes = "해당 에셋 라인을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetLineResponse> getAssetLine(
            @PathVariable("assetLineId") Long assetLineId
    ){
        return RestSuccessResponse.newInstance(
                assetLineService.getAssetLine(assetLineId)
        );
    }

    @GetMapping
    @ApiOperation(value = "전체 에셋 라인 조회", notes = "전체 에셋 라인을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetLineResponse>> getAllAssetLine(

    ){
        return RestSuccessResponse.newInstance(
                assetLineService.getAllAssetLine()
        );
    }

    @PostMapping
    @ApiOperation(value="에셋 라인 생성", notes = "에셋 라인을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetLineResponse> createAssetLine(
        @RequestBody @Valid AssetLineCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetLineService.createAssetLine(request)
        );
    }

    @PatchMapping("/{assetLineId}")
    @ApiOperation(value= "에셋 라인 업데이트", notes = "해당 에셋 라인을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetLineResponse> updateAssetLine(
            @RequestBody @Valid AssetLineUpdateRequest request,
            @PathVariable("assetLineId") Long assetLineId
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetLineService.updateAssetLine(assetLineId, request)
        );
    }

    @DeleteMapping("/{assetLineId}")
    @ApiOperation(value = "에셋 라인 삭제", notes = "해당 에셋 라인을 비활성화합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetLine(
            @PathVariable("assetLineId") Long id
    ){
        assetLineService.deleteAssetLine(id);
    }
}
