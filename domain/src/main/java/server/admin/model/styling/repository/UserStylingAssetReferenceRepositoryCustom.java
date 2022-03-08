package server.admin.model.styling.repository;

import server.admin.model.asset.dto.response.AssetPrototypeResponse;

import java.util.List;

public interface UserStylingAssetReferenceRepositoryCustom {
    List<AssetPrototypeResponse.Minified> findMinifiedByStylingId(Long id);
}
