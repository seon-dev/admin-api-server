package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.AssetBrandCategory;

public interface AssetBrandCategoryRepository extends JpaRepository<AssetBrandCategory, Long> {
}
