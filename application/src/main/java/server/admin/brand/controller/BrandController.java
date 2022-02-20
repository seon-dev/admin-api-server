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


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brand")
public class BrandController {
    private final BrandService brandService;
    private final int DEFAULT_SIZE = 25;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 조회", notes = "오프셋 페이지네이션에 맞게, 브랜드 미리보기 정보를 조회합니다. 디폴트 사이즈:25 디폴트 정렬기준:id,asc 디폴트 페이지:첫번째(0) enabled 디폴트:전체조회")
    public RestResponse<PageResult<BrandResponse.Minified>> getAllBrand(
            @PageableDefault(size = DEFAULT_SIZE, page = 0, sort = "id") final Pageable pageable,
            @RequestParam(value = "enabled", required = false) Boolean isEnabled
    ) {
        return RestSuccessResponse.newInstance(
                this.brandService.getAllBrand(pageable, isEnabled)
        );
    }

    @GetMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 정보 조회", notes = "브랜드 단건 정보를 조회합니다.")
    public BrandResponse getBrand(@PathVariable("brandId") Long brandId){
        return brandService.getBrand(brandId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 생성", notes = "브랜드 정보를 생성합니다.")
    public BrandResponse createBrand(@RequestBody @Valid BrandCreateRequest brand){
        return this.brandService.createBrand(brand);
    }

    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 삭제", notes = "브랜드 정보를 삭제합니다.")
    public void deleteBrand(@PathVariable("brandId") Long brandId){
        this.brandService.deleteBrand(brandId);
    }

    @PutMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 업데이트", notes = "브랜드 정보를 업데이트 합니다.")
    public BrandResponse updateBrand(
            @PathVariable("brandId") Long brandId,
            @RequestBody @Valid BrandUpdateRequest brand
    ){
        return this.brandService.updateBrand(brandId,brand);
    }
}
