package server.admin.service.brand;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.asset.repository.AssetBrandCategoryRepository;
import server.admin.model.brand.dto.request.BrandCreateRequest;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.dto.request.BrandUpdateRequest;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.io.IOException;
import java.util.Optional;

import static server.admin.model.brand.exception.BrandException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;
    private final AssetBrandCategoryRepository assetBrandCategoryRepository;
    private final S3Service s3Service;

    @Transactional(readOnly = true)
    public PageResult<BrandResponse.Minified> getAllBrand(Pageable pageable, Boolean isEnabled){
        return new PageResult<>(brandRepository.getAllBrand(pageable,isEnabled));
    }

    public void createBrand(BrandCreateRequest brandCreateDto) throws IOException {
        Brand brand = BrandCreateRequest.toEntity(brandCreateDto);
        brand.setResource(s3Service.upload(brandCreateDto.getResource()));
        brand.setResourceCard(s3Service.upload(brandCreateDto.getResourceCard()));
        brand.setResourceWallpaper(s3Service.upload(brandCreateDto.getResourceWallpaper()));
        brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand.get());
        brandResponse.setBrandCategories(assetBrandCategoryRepository.findMinifiedById(brandId));
        return brandResponse;

    }

    public void deleteBrand(Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        brand.ifPresent(
                singleBrand -> { singleBrand.setIsEnabled(false);}
        );
        if( !brand.isPresent()) {
            throw new BrandNotExistException();
        }
    }

    public BrandResponse updateBrand(Long brandId, BrandUpdateRequest brandUpdateDto) throws IOException {
        Optional<Brand> optionalBrand = this.brandRepository.findById(brandId);
        optionalBrand.orElseThrow(BrandNotExistException::new);
        Brand brand = optionalBrand.get();
        brandUpdateDto.toEntityExcept(brand);
        if(brandUpdateDto.getResource() != null) brand.setResource(s3Service.upload(brandUpdateDto.getResource()));
        if(brandUpdateDto.getResourceCard() != null) brand.setResourceCard(s3Service.upload(brandUpdateDto.getResourceCard()));
        if(brandUpdateDto.getResourceWallpaper() != null) brand.setResourceWallpaper(s3Service.upload(brandUpdateDto.getResourceWallpaper()));

        BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand);
        brandResponse.setBrandCategories(assetBrandCategoryRepository.findMinifiedById(brandId));
        return brandResponse;
    }

}

