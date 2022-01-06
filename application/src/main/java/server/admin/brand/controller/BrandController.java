package server.admin.brand.controller;

import server.admin.common.BasicMessage;
import server.admin.brand.model.dto.BrandCreateDto;
import server.admin.brand.model.dto.BrandResponseDto;
import server.admin.brand.model.service.BrandService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "브랜드 전체 조회", notes = "브랜드 전체 정보를 조회합니다.")
    public List<BrandResponseDto> getAllBrand() {
        return this.brandService.getAllBrand();
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

    //브랜드 수정로직
}
