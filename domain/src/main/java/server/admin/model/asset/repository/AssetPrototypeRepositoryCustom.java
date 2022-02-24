package server.admin.model.asset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.List;
import java.util.Optional;

public interface AssetPrototypeRepositoryCustom {
    Optional<AssetPrototype> findByIdWithFetchJoin(Long id);
    Page<AssetPrototypeResponse> getAllAssetPrototype(Pageable pageable, Boolean isEnabled);

}
