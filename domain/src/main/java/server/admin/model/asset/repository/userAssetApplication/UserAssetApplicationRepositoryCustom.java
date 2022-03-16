package server.admin.model.asset.repository.userAssetApplication;

import org.springframework.data.domain.Sort;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAsset;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.List;
import java.util.Optional;

public interface UserAssetApplicationRepositoryCustom {
    Optional<UserAssetApplication> findUserAssetApplicationById(Long id);
    List<UserAssetApplication> getUserAssetApplications(
//            Long cursorId, Integer size, Boolean isVerified, Sort sort
            Boolean isEnabled
    );
}
