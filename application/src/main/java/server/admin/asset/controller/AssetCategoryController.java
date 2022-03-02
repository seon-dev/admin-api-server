package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryUpdateRequest;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetCategoryService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/asset-category")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    @GetMapping("/{assetCategoryId}")
    @ApiOperation(value = "단일 에셋 카테고리 조회", notes = "해당 단일 에셋 카테고리을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetCategoryResponse> getAssetCategory(
            @PathVariable("assetCategoryId") Long assetCategoryId

    ){
        return RestSuccessResponse.newInstance (
                assetCategoryService.getAssetCategory(assetCategoryId)
        );
    }

    @GetMapping()
    @ApiOperation(value = "전체 에셋 카테고리 조회", notes = "전체 에셋 카테고리 리스트를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetCategoryResponse>> getAllAssetCategory(){
        return RestSuccessResponse.newInstance(
                assetCategoryService.getAllAssetCategory()
        );
    }

    @PostMapping
    @ApiOperation(value = "에셋 카테고리 생성", notes = "에셋 카테고리을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetCategoryResponse> createAssetCateory(
            @RequestBody @Valid AssetCategoryCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetCategoryService.createAssetCategory(request)
        );
    }

    @PatchMapping("/{assetCategoryId}")
    @ApiOperation(value = "에셋 카테고리 업데이트", notes = "해당 에셋 카테고리을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetCategoryResponse> updateAssetCategory(
            @PathVariable("assetCategoryId") Long id,
            @RequestBody @Valid AssetCategoryUpdateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetCategoryService.updateAssetCategory(id,request)
        );
    }

    @DeleteMapping("/{assetCategoryId}")
    @ApiOperation(value = "에셋 카테고리 삭제", notes = "해당 에샛 카테고리을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetCategory(
            @PathVariable("assetCategoryId") Long id
    ){
        assetCategoryService.deleteAssetCategory(id);
    }

}
