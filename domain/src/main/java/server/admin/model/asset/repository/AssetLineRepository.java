package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.AssetLine;

public interface AssetLineRepository extends JpaRepository<AssetLine, Long> {
}
