package server.admin.model.asset.repository;

import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.Optional;

public interface AssetPrototypeRepositoryCustom {
    Optional<AssetPrototype> findByIdWithFetchJoin(Long id);
}
