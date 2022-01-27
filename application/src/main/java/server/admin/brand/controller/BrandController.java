package server.admin.brand.controller;

import org.springframework.data.domain.PageRequest;
import server.admin.brand.model.dto.BrandUpdateDto;
import server.admin.brand.model.entity.Brand;
import server.admin.common.BasicMessage;
import server.admin.brand.model.dto.BrandCreateDto;
import server.admin.brand.model.dto.BrandResponseDto;
import server.admin.brand.model.service.BrandService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.common.cursor.CursorResult;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brand")
public class BrandController {
    private final BrandService brandService;
    private static final int DEFAULT_SIZE = 10;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 전체 조회", notes = "브랜드 전체 정보를 조회합니다.")
    public CursorResult<BrandResponseDto> getAllBrand(@RequestParam("page") Long cursorId) {
        return this.brandService.getAllBrand(cursorId, PageRequest.of(0,DEFAULT_SIZE));
    }

    @GetMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 정보 조회", notes = "브랜드 단건 정보를 조회합니다.")
    public BrandResponseDto getBrand(@PathVariable("brandId") Long brandId){
        return brandService.getBrand(brandId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 생성", notes = "브랜드 정보를 생성합니다.")
    public BrandResponseDto createBrand(@RequestBody BrandCreateDto brand){
        return this.brandService.createBrand(brand);
    }

    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 삭제", notes = "브랜드 정보를 삭제합니다.")
    public String deleteBrand(@PathVariable("brandId") Long brandId){
        BasicMessage basicMessage =  this.brandService.deleteBrand(brandId);
        return basicMessage.getMessage();
    }

    @PutMapping("/{brandId}/brand")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 업데이트", notes = "브랜드 정보를 업데이트 합니다.")
    public BrandResponseDto updateBrand(
            @PathVariable("brandId") Long brandId,
            @RequestBody BrandUpdateDto brand
    ){
        return this.brandService.updateBrand(brandId,brand);
    }
}
