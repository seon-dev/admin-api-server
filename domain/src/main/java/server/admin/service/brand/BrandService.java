package server.admin.service.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
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
import java.util.ArrayList;
import java.util.List;
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
    public PageResult<BrandResponse> getAllBrand(Pageable pageable){
        List<Brand> brandList = brandRepository.getAllBrand(pageable);
        List<BrandResponse> brandResponseList = new ArrayList<>();
        brandList.forEach(brand -> {
            BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand);
            List<AssetBrandCategoryResponse.Minified> minifiedList = assetBrandCategoryRepository.findMinifiedByBrandId(brand.getId());
            brandResponse.setBrandCategories(minifiedList);
            brandResponseList.add(brandResponse);
        });
        PageImpl<BrandResponse> pageResult = new PageImpl<>(brandResponseList, Pageable.unpaged(), brandResponseList.size());
        return new PageResult<>(pageResult);
    }

    public void createBrand(BrandCreateRequest request) throws Exception {
        Brand brand = BrandCreateRequest.toEntity(request);
        final String filename = request.getResourceFileName(null);
        s3Service.upload(request.getResourceUploaded(), filename);
        brand.setResource(filename);
        final String wallpapaerFilename = request.getResourceFileName("wallpapaer");
        s3Service.upload(request.getResourceWallpaperUploaded(), wallpapaerFilename);
        brand.setResourceWallpaper(wallpapaerFilename);
        final String cardFilename = request.getResourceFileName("card");
        s3Service.upload(request.getResourceCardUploaded(), cardFilename);
        brand.setResourceCard(cardFilename);
        brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand.get());
        brandResponse.setBrandCategories(assetBrandCategoryRepository.findMinifiedByBrandId(brandId));
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
            if(request.getResourceUploaded().equals("")) brand.setResource(null);
            else{
                final String filename = request.getResourceFileName(null, request.getResourceExtension());
                s3Service.upload(request.getResourceUploaded(), filename);
                brand.setResource(filename);
            }

        }
        if(request.getResourceCardUploaded() != null && request.getResourceCardExtension() != null) {
            if(request.getResourceCardUploaded().equals("")) brand.setResourceCard(null);
            else{
                final String filename = request.getResourceFileName("card", request.getResourceCardExtension());
                s3Service.upload(request.getResourceCardUploaded(), filename);
                brand.setResourceCard(filename);
            }

        }
        if(request.getResourceWallpaperUploaded() != null && request.getResourceWallpaperExtension() != null) {
            if(request.getResourceWallpaperUploaded().equals("")) brand.setResourceWallpaper(null);
            else{
                final String filename = request.getResourceFileName("wallpaper", request.getResourceWallpaperExtension());
                s3Service.upload(request.getResourceWallpaperUploaded(), filename);
                brand.setResourceWallpaper(filename);
            }

        }

        BrandResponse brandResponse = BrandResponse.toResponseWithoutBrandCategory(brand);
        brandResponse.setBrandCategories(assetBrandCategoryRepository.findMinifiedByBrandId(brandId));
        return brandResponse;
    }

}

