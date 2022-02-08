package server.admin.model.asset.repository;

import server.admin.model.asset.entity.UserAssetApplication;

public class UserAssetApplicationRepositoryImpl implements UserAssetApplicationRepositoryCustom{
    @Override
    public UserAssetApplication findByIdWithFetchJoin(Long id) {
        return null;
    }
}
