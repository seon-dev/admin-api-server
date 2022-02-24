package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import server.admin.model.asset.entity.AssetSeason;

public interface AssetSeasonRepository extends JpaRepository<AssetSeason, Long> {
    @Nullable
    AssetSeason findSeasonById(Long id);

}
