package server.admin.model.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.permission.entity.ModeratorPermission;
import server.admin.model.permission.entity.Permission;

public interface ModeratorPermissionRepository extends JpaRepository<ModeratorPermission, Long>,ModeratorPermissionRepositoryCustom {
}
