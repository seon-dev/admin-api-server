package server.admin.model.badge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.badge.entity.Badge;
import server.admin.model.user.entity.User;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long>,BadgeRepositoryCustom {
    Page<Badge> findAllByOrderByIdAsc(Pageable pageable,Boolean isEnabled);
    Page<Badge> findByIdGreaterThanEqualOrderByIdAsc(Long id, Pageable pageable, Boolean isEnabled);
    Boolean existsByIdGreaterThan(Long id);
    @Nullable
    Badge findBadgeById(Long id);
}
