package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.AssetCategory;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {
}
