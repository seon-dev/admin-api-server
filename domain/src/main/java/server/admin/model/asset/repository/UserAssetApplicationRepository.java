package server.admin.model.asset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.UserAssetApplication;

import java.util.List;

public interface UserAssetApplicationRepository extends JpaRepository<UserAssetApplication, Long>, UserAssetApplicationRepositoryCustom {
//    List<UserAssetApplication> findByIdLessThanEqualOrderByIdDesc(Long id);
    List<UserAssetApplication> findAllByIsVerifiedEqualsOrderByIdDesc(Boolean isVerified);
    Boolean existsByIdLessThan(Long id);
}
