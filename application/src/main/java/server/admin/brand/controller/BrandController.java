package server.admin.brand.controller;

import server.admin.model.brand.dto.request.BrandUpdateRequest;
import server.admin.model.brand.dto.request.BrandCreateRequest;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.service.brand.BrandService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brand")
public class BrandController {
    private final BrandService brandService;
    private final String DEFAULT_SIZE = "5";

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "브랜드 조회", notes = "브랜드 정보를 커서 페이징 베이스로 조회합니다.")
//    public CursorResult<BrandResponse> getAllBrand(@RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size, @RequestParam("cursorId") Long cursorId) {
//        return this.brandService.getAllBrand(cursorId, PageRequest.of(0,size));
//    }

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
