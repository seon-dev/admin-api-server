package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import server.admin.model.asset.dto.response.AssetCategoryResponse;
import server.admin.model.asset.entity.AssetCategory;
import server.admin.model.asset.exception.AssetCategoryException;
import server.admin.model.asset.repository.AssetCategoryRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AssetCategoryService {
    private final AssetCategoryRepository assetCategoryRepository;

    public AssetCategoryResponse getAssetCategory(Long assetCategoryId){
        Optional<AssetCategory> optionalAssetCategory = assetCategoryRepository.findById(assetCategoryId);
        return AssetCategoryResponse.toResponse(optionalAssetCategory.orElseThrow(AssetCategoryException.AssetCategoryNotExistException::new));
    }


}
