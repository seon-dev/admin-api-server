package server.admin.service.brand;

import org.springframework.data.domain.Pageable;
import server.admin.model.asset.repository.assetBrandCategory.AssetBrandCategoryRepository;
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

    public void createBrand(BrandCreateRequest request) throws Exception {
        Brand brand = BrandCreateRequest.toEntity(request);
        final String filename = request.getResourceFileName(null);
        s3Service.upload(request.getResourceUploaded(), filename);
        brand.setResource(filename);
        final String wallpapaerFilename = request.getResourceFileName("wallpapaer");
        s3Service.upload(request.getResourceWallpaperUploaded(), filename);
        brand.setResourceWallpaper(filename);
        final String cardFilename = request.getResourceFileName("card");
        s3Service.upload(request.getResourceCardUploaded(), filename);
        brand.setResourceCard(filename);        brandRepository.save(brand);
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

    public BrandResponse updateBrand(Long brandId, BrandUpdateRequest request) throws Exception {
        Optional<Brand> optionalBrand = this.brandRepository.findById(brandId);
        optionalBrand.orElseThrow(BrandNotExistException::new);
        Brand brand = request.setEntityExcept(optionalBrand.get(), request);
        if(request.getResourceUploaded() != null && request.getResourceExtension() != null) {
            final String filename = request.getResourceFileName(null);
            s3Service.upload(request.getResourceUploaded(), filename);
            brand.setResource(filename);
        }
        if(request.getResourceCardUploaded() != null && request.getResourceCardExtension() != null) {
            final String filename = request.getResourceFileName("card");
            s3Service.upload(request.getResourceCardUploaded(), filename);
            brand.setResourceCard(filename);
        }
        if(request.getResourceWallpaperUploaded() != null && request.getResourceWallpaperExtension() != null) {
            final String filename = request.getResourceFileName("wallpaper");
            s3Service.upload(request.getResourceWallpaperUploaded(), filename);
            brand.setResourceWallpaper(filename);
        }

        BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand);
        brandResponse.setBrandCategories(assetBrandCategoryRepository.findMinifiedById(brandId));
        return brandResponse;
    }

}

