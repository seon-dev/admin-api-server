package server.admin.service.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.request.BrandCreateRequest;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.dto.request.BrandUpdateRequest;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.common.cursor.CursorResult;

import java.util.List;
import java.util.Optional;

import static server.admin.model.brand.exception.BrandException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    private List<Brand> getBrandsWithPage(Long cursorId, Pageable pageable){
        return cursorId == null ? brandRepository.findAllByIsEnabledEqualsOrderByIdAsc(true): brandRepository.findByIdGreaterThanEqualAndIsEnabledEqualsOrderByIdAsc(cursorId,true);
    }

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return brandRepository.existsByIdGreaterThan(lastId);
    }

    @Transactional(readOnly = true)
    public CursorResult<BrandResponse> getAllBrand(Long cursorId, Pageable pageable){
        final List<Brand> allWithPagination = this.getBrandsWithPage(cursorId, pageable).stream().limit(5).toList();
        final List<BrandResponse> allDtoWithPagination = allWithPagination
                .stream().map(BrandResponse::toResponse)
                .toList();

//        final List<Brand> brandList = allWithPagination.getContent();
        final Long lastIdOfList = !allWithPagination.isEmpty() ? allDtoWithPagination.get(allDtoWithPagination.size()-1).getId() : null;

        return new CursorResult<>(allDtoWithPagination, hasNext(lastIdOfList));
    }

    public BrandResponse createBrand(BrandCreateRequest brandCreateDto){
        Brand brand = this.brandRepository.save(Brand.toEntity(brandCreateDto));
        return BrandResponse.toResponse(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        return BrandResponse.toResponse(brand.get());
    }

    public void deleteBrand(Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        brand.ifPresentOrElse(
                singleBrand -> { singleBrand.setIsEnabled(false);},
                () -> {
                    throw new BrandNotExistException();
                }
        );
    }

    public BrandResponse updateBrand(Long brandId, BrandUpdateRequest brandUpdateDto){
        Optional<Brand> optionalBrand = this.brandRepository.findById(brandId);
        optionalBrand.orElseThrow(BrandNotExistException::new);
            Brand brand = optionalBrand.get();
            brand.setColor(brandUpdateDto.getColor());
            brand.setName(brandUpdateDto.getName());
            brand.setDescription(brandUpdateDto.getDescription());
            brand.setOriginalName(brandUpdateDto.getOriginalName());
            brand.setRecommendation(brandUpdateDto.getRecommendation());
            brand.setResource(brandUpdateDto.getResource());
            brand.setResourceCard(brandUpdateDto.getResourceCard());
            brand.setResourceWallpaper(brandUpdateDto.getResourceWallpaper());
            return BrandResponse.toResponse(brand);

    }

}

