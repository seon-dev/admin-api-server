package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetBrandCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryUpdateRequest;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.asset.entity.AssetCategory;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.asset.exception.AssetBrandCategoryException;
import server.admin.model.asset.exception.AssetCategoryException;
import server.admin.model.asset.repository.AssetCategoryRepository;
import server.admin.model.asset.repository.assetBrandCategory.AssetBrandCategoryRepository;
import server.admin.model.asset.repository.assetLine.AssetLineRepository;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.brand.repository.BrandRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetBrandCategoryService {
    private final AssetBrandCategoryRepository assetBrandCategoryRepository;
    private final AssetLineRepository assetLineRepository;
    private final AssetCategoryRepository assetCategoryRepository;
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public AssetBrandCategoryResponse getAssetBrandCategory(Long id){
        Optional<AssetBrandCategory> optionalAssetBrandCategory = assetBrandCategoryRepository.findByIdFetchJoin(id);
        AssetBrandCategory brandCategory = optionalAssetBrandCategory.orElseThrow(AssetBrandCategoryException.AssetBrandCategoryNotExistException::new);
        AssetBrandCategoryResponse assetBrandCategoryResponse = AssetBrandCategoryResponse.toResponseWithoutAssetLine(brandCategory);

        List<AssetLine> assetLineList = assetLineRepository.findByBrandCategory(brandCategory);
        List<AssetLineResponse> assetLineResponses = new ArrayList<>();
        assetLineList.forEach(assetLine -> {
            assetLineResponses.add(AssetLineResponse.toResponse(assetLine));
        });
        assetBrandCategoryResponse.setLines(assetLineResponses);
        return assetBrandCategoryResponse;
    }

    @Transactional(readOnly = true)
    public PageResult<AssetBrandCategoryResponse> getAllAssetBrandCategory(){
        List<AssetBrandCategory> assetBrandCategoryList = assetBrandCategoryRepository.findAllFetchJoin();
        List<AssetBrandCategoryResponse> assetBrandCategoryResponseList = new ArrayList<>();

        assetBrandCategoryList.forEach(assetBrandCategory -> {
            assetBrandCategoryResponseList.add(AssetBrandCategoryResponse.toResponseWithoutAssetLine(assetBrandCategory));

        });

        assetBrandCategoryResponseList.forEach(assetBrandCategoryResponse -> {
            assetBrandCategoryResponse.setLines(
                (assetLineRepository.findResponseByBrandCategoryId(assetBrandCategoryResponse.getId()))//list<assetline>
            );
        });
        PageImpl<AssetBrandCategoryResponse> pageResult = new PageImpl<>(assetBrandCategoryResponseList, Pageable.unpaged(), assetBrandCategoryResponseList.size());
        return new PageResult(pageResult);
    }

    public AssetBrandCategoryResponse createAssetBrandCategory(AssetBrandCategoryCreateRequest request) throws Exception {
        AssetBrandCategory brandCategory = new AssetBrandCategory();
        brandCategory.setIsEnabled(request.getIsEnabled());
        brandCategory.setCategory(assetCategoryRepository.findById(request.getAssetCategoryId()).orElseThrow(AssetCategoryException.AssetCategoryNotExistException::new));
        brandCategory.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(BrandException.BrandNotExistException::new));
        AssetBrandCategoryResponse assetBrandCategoryResponse = AssetBrandCategoryResponse.toResponseWithoutAssetLine(assetBrandCategoryRepository.save(brandCategory));

        assetBrandCategoryResponse.setLines(assetLineRepository.findResponseByBrandCategoryId(assetBrandCategoryResponse.getId()));
        return assetBrandCategoryResponse;
    }

//    public AssetBrandCategoryResponse updateBrandAssetCategory(Long assetPrototypeId, AssetBrandCategoryUpdateRequest request) throws Exception {
//    }

    public void deleteAssetBrandCategory(Long id){
        Optional<AssetBrandCategory> optionalAssetBrandCategory = assetBrandCategoryRepository.findById(id);
        optionalAssetBrandCategory.orElseThrow(AssetBrandCategoryException.AssetBrandCategoryNotExistException::new).setIsEnabled(false);
    }
}
