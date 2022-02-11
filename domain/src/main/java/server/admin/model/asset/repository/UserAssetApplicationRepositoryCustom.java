package server.admin.model.asset.repository;

import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAsset;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.List;
import java.util.Optional;

public interface UserAssetApplicationRepositoryCustom {
    Optional<UserAssetApplicationResponse> findResponseById(Long id);
    List<UserAssetApplicationResponse> getUserAssetApplications(Long cursorId, Integer size, Boolean isVerified);
}
