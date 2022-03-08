package server.admin.model.asset.repository.assetBrandCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.user.dto.response.UserProfileResponse;

import java.util.List;
import java.util.Optional;

public interface AssetBrandCategoryRepositoryCustom {
    List<AssetBrandCategoryResponse.Minified> findMinifiedByBrandId(Long id);
    Optional<AssetBrandCategory> findByIdFetchJoin(Long id);
    List<AssetBrandCategory> findAllFetchJoin();
}
