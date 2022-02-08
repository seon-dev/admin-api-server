package server.admin.model.asset.repository;

import server.admin.model.asset.entity.UserAssetApplication;

public interface UserAssetApplicationRepositoryCustom {
    UserAssetApplication findByIdWithFetchJoin(Long id);
}
