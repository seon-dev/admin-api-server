package server.admin.service.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.BrandCreateDto;
import server.admin.model.brand.dto.BrandResponseDto;
import server.admin.model.brand.dto.BrandUpdateDto;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.brand.repository.BrandRepository;
import server.admin.model.common.BasicMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.common.cursor.CursorResult;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static server.admin.model.brand.exception.BrandException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    private Page<Brand> getBrandsWithPage(Long cursorId, Pageable pageable){
        return cursorId == null ? brandRepository.findAllByOrderByIdAsc(pageable): brandRepository.findByIdGreaterThanEqualOrderByIdAsc(cursorId,pageable);
    }

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return brandRepository.existsByIdGreaterThan(lastId);
    }

    @Transactional(readOnly = true)
    public CursorResult<BrandResponseDto> getAllBrand(Long cursorId, Pageable pageable){
        final Page<Brand> allWithPagination = this.getBrandsWithPage(cursorId, pageable);
        final Page<BrandResponseDto> allDtoWithPagination = new PageImpl<>(allWithPagination
                .map(BrandResponseDto::toResponse)
                .toList());

        final List<Brand> brandList = allWithPagination.getContent();
        final Long lastIdOfList = !allWithPagination.isEmpty() ? brandList.get(brandList.size()-1).getId() : null;

        return new CursorResult<>(allDtoWithPagination, hasNext(lastIdOfList));
    }

    public BrandResponseDto createBrand(BrandCreateDto brandCreateDto){
        Brand brand = this.brandRepository.save(Brand.toEntity(brandCreateDto));
        return BrandResponseDto.toResponse(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponseDto getBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        return BrandResponseDto.toResponse(brand.get());
    }

    public BasicMessage deleteBrand(Long brandId) {
        Optional<Brand> brand = this.brandRepository.findById(brandId);
        brand.orElseThrow(BrandNotExistException::new);
        brandRepository.delete(brand.get());
        return new BasicMessage("브랜드가 성공적으로 삭제되었습니다.");

    }

    public BrandResponseDto updateBrand(Long brandId, BrandUpdateDto brandUpdateDto){
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
            return BrandResponseDto.toResponse(brand);

    }

}

