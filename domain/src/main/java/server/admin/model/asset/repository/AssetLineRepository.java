package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import server.admin.model.asset.entity.AssetLine;

public interface AssetLineRepository extends JpaRepository<AssetLine, Long> {
    @Nullable
    AssetLine findLineById(Long id);
}
