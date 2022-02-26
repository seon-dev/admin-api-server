package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetCategoryService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/asset-category")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    @GetMapping("/{assetCategoryId}")
    @ApiOperation(value = "단일 에셋 카테고리 조회", notes = "단일 에셋 프로토타입을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetCategoryResponse> getAssetCategory(
            @PathVariable("assetCategoryId") Long assetCategoryId
    ){
        return RestSuccessResponse.newInstance (
                assetCategoryService.getAssetCategory(assetCategoryId)
        );
    }

//    @GetMapping()
//    @ApiOperation(value = "에셋 프로토타입", notes = "페이지네이션에 맞는 에셋 프로토타입을 조회합니다.")
}
