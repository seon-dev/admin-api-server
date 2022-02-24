package server.admin.model.asset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.user.dto.response.UserProfileResponse;

import java.util.List;

public interface AssetBrandCategoryRepositoryCustom {
    List<AssetBrandCategoryResponse.Minified> findMinifiedById(Long id);
}
