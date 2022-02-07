package server.admin.model.asset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.asset.entity.UserAssetApplication;

public interface UserAssetApplicationRepository extends JpaRepository<UserAssetApplication, Long> {
    Page<UserAssetApplication> findByIdGreaterThanEqualAndIsEnabledEqualsOrderByIdAsc(Long id, Pageable pageable);
    Page<UserAssetApplication> findAllByIsEnabledEqualsOrderByIdAsc(Pageable pageable);
    Boolean existsByIdGreaterThan(Long id);

}
