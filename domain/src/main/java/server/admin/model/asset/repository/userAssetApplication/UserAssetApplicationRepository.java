package server.admin.model.asset.repository.userAssetApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.List;

public interface UserAssetApplicationRepository extends JpaRepository<UserAssetApplication, Long>, UserAssetApplicationRepositoryCustom {
//    List<UserAssetApplication> findByIdLessThanEqualOrderByIdDesc(Long id);
    List<UserAssetApplication> findAllByIsVerifiedEqualsOrderByIdDesc(Boolean isVerified);
    Boolean existsByIdLessThan(Long id);
}
