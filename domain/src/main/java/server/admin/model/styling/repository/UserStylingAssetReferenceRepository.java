package server.admin.model.styling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.styling.entity.UserStylingAssetReference;

public interface UserStylingAssetReferenceRepository extends JpaRepository<UserStylingAssetReference, Long>, UserStylingAssetReferenceRepositoryCustom {
}
