package server.admin.model.permission.repository;

import server.admin.model.permission.entity.ModeratorPermission;
import server.admin.model.permission.entity.Permission;

public interface ModeratorPermissionRepositoryCustom {
    ModeratorPermission findByPermissionFetchJoin(Permission permission);
}
