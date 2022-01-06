package server.admin.brand.model.service;

import server.admin.brand.model.dto.BrandCreateDto;
import server.admin.brand.model.repository.BrandRepository;
import server.admin.common.BasicMessage;
import server.admin.brand.model.dto.BrandResponseDto;
import server.admin.brand.model.dto.BrandUpdateDto;
import server.admin.brand.model.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public List<BrandResponseDto> getAllBrand(){
        List<Brand> all = this.brandRepository.findAll();
        List<BrandResponseDto> responseList = new ArrayList<>();
        all.forEach(brand -> {
            BrandResponseDto brandResponseDto = new BrandResponseDto(brand);
            responseList.add(brandResponseDto);
        });
        return responseList;
    }

    public BrandResponseDto createBrand(BrandCreateDto brandCreateDto){
        Brand brand = new Brand(brandCreateDto);
        this.brandRepository.save(brand);
        return new BrandResponseDto(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponseDto getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        if (brand.isPresent()) {
            return new BrandResponseDto(brand.get());
        } else {
            throw new RuntimeException("해당되는 브랜드가 존재하지 않습니다.");
        }
    }

    public BasicMessage deleteBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        if(brand.isPresent()){
            brandRepository.delete(brand.get());
            return new BasicMessage("브랜드가 성공적으로 삭제되었습니다.");
        } else throw new RuntimeException("해당되는 브랜드가 존재하지 않습니다.");

    }

    public BrandResponseDto updateBrand(Long brandId, BrandUpdateDto brandUpdateDto){
        Optional<Brand> optionalBrand = this.brandRepository.findById(brandId);
        if (optionalBrand.isPresent()){
            Brand brand = optionalBrand.get();
            brand.setColor(brandUpdateDto.getColor());
            brand.setName(brandUpdateDto.getName());
            brand.setDescription(brandUpdateDto.getDescription());
            brand.setOriginalName(brandUpdateDto.getOriginalName());
            brand.setRecommendation(brandUpdateDto.getRecommendation());
            brand.setResource(brandUpdateDto.getResource());
            brand.setResourceCard(brandUpdateDto.getResourceCard());
            brand.setResourceWallpaper(brandUpdateDto.getResourceWallpaper());
            return new BrandResponseDto(brand);
        } else{
            throw new RuntimeException("해당하는 브랜드가 존재하지 않습니다.");
        }
    }

}

