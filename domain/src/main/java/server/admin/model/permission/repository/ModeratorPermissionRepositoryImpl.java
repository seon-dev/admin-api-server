package server.admin.model.permission.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.permission.entity.ModeratorPermission;
import server.admin.model.permission.entity.Permission;

import javax.persistence.EntityManager;

public class ModeratorPermissionRepositoryImpl extends QueryDslSupport implements ModeratorPermissionRepositoryCustom {

    @Autowired
    public ModeratorPermissionRepositoryImpl(EntityManager entityManager){
        super(ModeratorPermission.class,entityManager);
    }


    @Override
    public ModeratorPermission findByPermissionFetchJoin(Permission permission) {
        return null;
    }
}

