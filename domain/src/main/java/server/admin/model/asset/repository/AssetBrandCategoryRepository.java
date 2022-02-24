package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import server.admin.model.asset.entity.AssetBrandCategory;

import java.util.List;

public interface AssetBrandCategoryRepository extends JpaRepository<AssetBrandCategory, Long>, AssetBrandCategoryRepositoryCustom {
    @Nullable
    AssetBrandCategory findBrandCategoryById(Long id);
}
