package server.admin.service.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.request.BrandCreateRequest;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.dto.request.BrandUpdateRequest;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.utils.page.PageResult;

import java.util.Optional;

import static server.admin.model.brand.exception.BrandException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public PageResult<BrandResponse.Minified> getAllBrand(Pageable pageable, Boolean isEnabled){
        return new PageResult<>(brandRepository.getAllBrand(pageable,isEnabled));
    }

    public BrandResponse createBrand(BrandCreateRequest brandCreateDto){
        Brand brand = this.brandRepository.save(Brand.toEntity(brandCreateDto));
        return BrandResponse.toResponseWithoutBrandCategory(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        return BrandResponse.toResponseWithoutBrandCategory(brand.get());
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
            return BrandResponse.toResponseWithoutBrandCategory(brand);

    }

}

