package server.admin.model.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.asset.entity.AssetPrototype;
@Repository
public interface AssetPrototypeRepository extends JpaRepository<AssetPrototype, Long>, AssetPrototypeRepositoryCustom {

}
