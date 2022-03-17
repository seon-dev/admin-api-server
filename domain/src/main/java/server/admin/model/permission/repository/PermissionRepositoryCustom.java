package server.admin.model.permission.repository;

import server.admin.model.permission.entity.Permission;

import java.util.List;

public interface PermissionRepositoryCustom {
    List<Permission> findAllFetchJoin();
}
