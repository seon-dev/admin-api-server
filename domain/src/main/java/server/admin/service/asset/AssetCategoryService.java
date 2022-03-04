package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetCategoryCreateRequest;
import server.admin.model.asset.dto.request.AssetCategoryUpdateRequest;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.asset.entity.AssetCategory;
import server.admin.model.asset.exception.AssetCategoryException;
import server.admin.model.asset.repository.AssetCategoryRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class AssetCategoryService {
    private final AssetCategoryRepository assetCategoryRepository;
    private final S3Service s3Service;

    @Transactional(readOnly = true)
    public AssetCategoryResponse getAssetCategory(Long assetCategoryId){
        Optional<AssetCategory> optionalAssetCategory = assetCategoryRepository.findById(assetCategoryId);
        return AssetCategoryResponse.toResponse(optionalAssetCategory.orElseThrow(AssetCategoryException.AssetCategoryNotExistException::new));
    }

    @Transactional(readOnly = true)
    public PageResult<AssetCategoryResponse> getAllAssetCategory(){
        List<AssetCategory> all = assetCategoryRepository.findAll();
        List<AssetCategoryResponse> allResponse = new ArrayList<>();
        all.forEach(assetCategory -> {
            allResponse.add(AssetCategoryResponse.toResponse(assetCategory));
        });
        PageImpl<AssetCategoryResponse> pageResult = new PageImpl<>(allResponse, Pageable.unpaged(), allResponse.size());
        return new PageResult(pageResult);
    }

    public AssetCategoryResponse createAssetCategory(AssetCategoryCreateRequest request) throws Exception {
        AssetCategory assetCategory = request.toEntityExceptResource(request);
        s3Service.upload(request.getResourceUploaded(), request.getResourceFileName());
        assetCategory.setResource(request.getResourceFileName());
        assetCategoryRepository.save(assetCategory);
        return AssetCategoryResponse.toResponse(assetCategory);
    }

    public AssetCategoryResponse updateAssetCategory(Long assetPrototypeId,AssetCategoryUpdateRequest request) throws Exception {
        Optional<AssetCategory> optionalAssetCategory = assetCategoryRepository.findById(assetPrototypeId);
        AssetCategory assetCategory = optionalAssetCategory.orElseThrow(AssetCategoryException.AssetCategoryNotExistException::new);
        if(request.getName() != null) assetCategory.setName(request.getName());
        if(request.getResourceUploaded() != null && request.getResourceExtension() != null) {
            s3Service.upload(request.getResourceUploaded(), request.getResourceFileName());
            assetCategory.setResource(request.getResourceFileName());
        }
        return AssetCategoryResponse.toResponse(assetCategory);
    }

    public void deleteAssetCategory(Long id){
        Optional<AssetCategory> optionalAssetCategory = assetCategoryRepository.findById(id);
        optionalAssetCategory.orElseThrow(AssetCategoryException.AssetCategoryNotExistException::new).setIsEnabled(false);
    }

}
