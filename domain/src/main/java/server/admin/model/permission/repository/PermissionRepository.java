package server.admin.model.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.permission.entity.Permission;
import server.admin.model.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Long>, PermissionRepositoryCustom {
    List<Permission> findByUser(User user);
}
