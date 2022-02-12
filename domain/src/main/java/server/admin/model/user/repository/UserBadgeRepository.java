package server.admin.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.user.entity.UserBadge;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
//    Optional<List<BadgeResponse>> findByUser(Long id);

}
