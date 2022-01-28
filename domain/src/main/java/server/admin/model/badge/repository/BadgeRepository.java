package server.admin.model.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.badge.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
