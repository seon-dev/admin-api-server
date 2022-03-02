package server.admin.model.asset.repository.assetLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.asset.entity.AssetLine;

import java.util.List;

public interface AssetLineRepository extends JpaRepository<AssetLine, Long>, AssetLineRepositoryCustom {
    @Nullable
    AssetLine findLineById(Long id);

    List<AssetLine> findByBrandCategory(AssetBrandCategory assetBrandCategory);
}
