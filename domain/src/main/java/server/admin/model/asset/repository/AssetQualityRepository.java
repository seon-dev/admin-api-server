package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.AssetQuality;

public interface AssetQualityRepository extends JpaRepository<AssetQuality, Long> {
}
