package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.AssetPrototypeCreateRequest;
import server.admin.model.asset.dto.request.AssetPrototypeUpdateRequest;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.service.asset.AssetPrototypeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/asset-prototype")
@RequiredArgsConstructor
public class AssetPrototypeController {
    private final AssetPrototypeService assetPrototypeService;
    private final String DEFAULT_SIZE = "5";
//1. response에 id넣기
    @PostMapping()
    @ApiOperation(value= "에셋 프로토타입 생성", notes = "에셋 프로토타입을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public AssetPrototypeResponse createAssetPrototype(
            @RequestBody @Valid AssetPrototypeCreateRequest request
    ){
        return assetPrototypeService.createAssetPrototype(request);
    }

//    @GetMapping()
//    @ApiOperation(value= "에셋 프로토타입 조회", notes = "페이지네이션에 맞는 에셋 프로토타입을 조회합니다.")
//    @ResponseStatus(HttpStatus.OK)
//    public Page<AssetPrototypeResponse> getAllAssetPrototype(
//            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
//            @RequestParam("cursorId") Long cursorId
//    ){
//        return assetPrototypeService.getAllAssetPrototype(cursorId, PageRequest.of(0, size));
//    }

    @GetMapping("/{assetPrototypeId}")
    @ApiOperation(value= "단일 에셋 프로토타입 조회", notes = "해당하는 에셋 프로토타입을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public AssetPrototypeResponse getAssetPrototype(@PathVariable("assetPrototypeId") Long assetPrototypeId){
        return assetPrototypeService.getAssetPrototype(assetPrototypeId);//페치조인으로 Get하는걸로 수정하기
    }

    @PutMapping("/{assetPrototypeId}")
    @ApiOperation(value = "에셋 프로토타입 업데이트", notes = "에셋 프로토타입을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public AssetPrototypeResponse updateAssetPrototype(
            @PathVariable("assetPrototypeId") Long assetPrototypeId,
            @RequestBody @Valid AssetPrototypeUpdateRequest request
    ){
        return assetPrototypeService.updateAssetPrototype(assetPrototypeId, request);
    }

    @DeleteMapping("/{assetPrototypeId}")
    @ApiOperation(value = "에셋 프로토타입 삭제", notes = "에셋 프로토타입을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssetPrototype(
            @PathVariable("assetPrototypeId") Long assetPrototypeId
    ){
        assetPrototypeService.deleteAssetPrototype(assetPrototypeId);
    }
}
