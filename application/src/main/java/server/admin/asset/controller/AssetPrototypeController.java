package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetPrototypeCreateRequest;
import server.admin.model.asset.dto.request.AssetPrototypeUpdateRequest;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.asset.AssetPrototypeService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/asset-prototype")
@RequiredArgsConstructor
public class AssetPrototypeController {
    private final AssetPrototypeService assetPrototypeService;
    private final int DEFAULT_SIZE = 25;


    @PostMapping()
    @ApiOperation(value= "에셋 프로토타입 생성", notes = "에셋 프로토타입을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public AssetPrototypeResponse createAssetPrototype(
            @Valid AssetPrototypeCreateRequest request
    ) throws IOException {
        return assetPrototypeService.createAssetPrototype(request);
    }

    @GetMapping()
    @ApiOperation(value= "에셋 프로토타입 조회", notes = "전체 에셋 프로토타입 리스트를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AssetPrototypeResponse>> getAllAssetPrototype(
            @PageableDefault(size = DEFAULT_SIZE, page = 0, sort = "id") final Pageable pageable
//            @RequestParam(value = "enabled", required = false) Boolean isEnabled
    ){
        return RestSuccessResponse.newInstance(
                assetPrototypeService.getAllAssetPrototype(Pageable.unpaged())
        );
    }

    @GetMapping("/{assetPrototypeId}")
    @ApiOperation(value= "단일 에셋 프로토타입 조회", notes = "해당하는 에셋 프로토타입을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetPrototypeResponse> getAssetPrototype(@PathVariable("assetPrototypeId") Long assetPrototypeId){
        return RestSuccessResponse.newInstance(
                assetPrototypeService.getAssetPrototype(assetPrototypeId)
        );
    }
//
    @PatchMapping("/{assetPrototypeId}")
    @ApiOperation(value = "에셋 프로토타입 업데이트", notes = "에셋 프로토타입을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AssetPrototypeResponse> updateAssetPrototype(
            @PathVariable("assetPrototypeId") Long assetPrototypeId,
            @Valid AssetPrototypeUpdateRequest request
    ) throws IOException {
        return RestSuccessResponse.newInstance(
                assetPrototypeService.updateAssetPrototype(assetPrototypeId, request)
        );
    }
//
    @DeleteMapping("/{assetPrototypeId}")
    @ApiOperation(value = "에셋 프로토타입 삭제", notes = "에셋 프로토타입을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetPrototype(
            @PathVariable("assetPrototypeId") Long assetPrototypeId
    ){
        assetPrototypeService.deleteAssetPrototype(assetPrototypeId);
    }
}
