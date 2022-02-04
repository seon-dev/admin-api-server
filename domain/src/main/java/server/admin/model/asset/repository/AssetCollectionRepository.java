package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.AssetCollection;

public interface AssetCollectionRepository extends JpaRepository<AssetCollection, Long> {
}
