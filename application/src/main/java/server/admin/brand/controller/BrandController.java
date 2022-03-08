package server.admin.brand.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import server.admin.model.brand.dto.request.BrandUpdateRequest;
import server.admin.model.brand.dto.request.BrandCreateRequest;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.brand.BrandService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brand")
public class BrandController {
    private final BrandService brandService;
    private final int DEFAULT_SIZE = 25;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 조회", notes = "전체 브랜드 정보를 조회합니다.")
    public RestResponse<PageResult<BrandResponse>> getAllBrand(
//            @PageableDefault(size = DEFAULT_SIZE, page = 0, sort = "id") final Pageable pageable,
//            @RequestParam(value = "enabled", required = false) Boolean isEnabled
    ) {
        return RestSuccessResponse.newInstance(
                this.brandService.getAllBrand(Pageable.unpaged())
        );
    }

    @GetMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 정보 조회", notes = "브랜드 단건 정보를 조회합니다.")
    public RestResponse<BrandResponse> getBrand(@PathVariable("brandId") Long brandId){
        return RestSuccessResponse.newInstance(
                brandService.getBrand(brandId)
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 생성", notes = "브랜드 정보를 생성합니다.")
    public void createBrand(
            @RequestBody @Valid BrandCreateRequest request
    ) throws Exception {
            brandService.createBrand(request);
    }

    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 삭제", notes = "브랜드 정보를 삭제합니다.")
    public void deleteBrand(@PathVariable("brandId") Long brandId){
        this.brandService.deleteBrand(brandId);
    }

    @PatchMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 업데이트", notes = "브랜드 정보를 업데이트 합니다.")
    public RestResponse<BrandResponse> updateBrand(
            @PathVariable("brandId") Long brandId,
            @RequestBody @Valid BrandUpdateRequest brand
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                this.brandService.updateBrand(brandId,brand)
        );
    }
}
