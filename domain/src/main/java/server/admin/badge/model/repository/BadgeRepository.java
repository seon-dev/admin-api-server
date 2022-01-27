package server.admin.badge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.badge.model.entity.Badge;
@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
