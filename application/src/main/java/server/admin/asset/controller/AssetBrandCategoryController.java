package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetBrandCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryUpdateRequest;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetBrandCategoryService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/asset-brand-category")
public class AssetBrandCategoryController {
    private final AssetBrandCategoryService assetBrandCategoryService;

    @GetMapping("/{assetBrandCategoryId}")
    @ApiOperation(value = "단일 에셋 브랜드 카테코리 조회", notes = "해당 단일 에셋 프로토타입을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetBrandCategoryResponse> getAssetBrandCategory(
            @PathVariable("assetBrandCategoryId") Long assetBrandCategoryId

    ){
        return RestSuccessResponse.newInstance (
                assetBrandCategoryService.getAssetBrandCategory(assetBrandCategoryId)
        );
    }

    @GetMapping()
    @ApiOperation(value = "전체 에셋 브랜드 카테코리 조회", notes = "전체 에셋 브랜드 카테고리를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetBrandCategoryResponse>> getAllAssetBrandCategory(){
        return RestSuccessResponse.newInstance(
                assetBrandCategoryService.getAllAssetBrandCategory()
        );
    }

    @PostMapping
    @ApiOperation(value = "에셋 브랜드 카테코리 생성", notes = "에셋 브랜드 카테고리을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetBrandCategoryResponse> createAssetBrandCateory(
            @RequestBody @Valid AssetBrandCategoryCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                assetBrandCategoryService.createAssetBrandCategory(request)
        );
    }


    @DeleteMapping("/{assetBrandCategoryId}")
    @ApiOperation(value = "에셋 브랜드 카테코리 삭제", notes = "해당 에셋 브랜드 카테고리를 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetCategory(
            @PathVariable("assetBrandCategoryId") Long id
    ){
        assetBrandCategoryService.deleteAssetBrandCategory(id);
    }
}
