package server.admin.model.asset.repository.assetLine;

import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.asset.entity.AssetLine;

import java.util.List;
import java.util.Optional;

public interface AssetLineRepositoryCustom {
    Optional<AssetLine> findByIdFetchJoin(Long id);
    List<AssetLine> findAllFetchJoin();
    List<AssetLineResponse> findResponseByBrandCategoryId(Long id);
}
