package server.admin.model.permission.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.permission.entity.Permission;

import javax.persistence.EntityManager;

public class PermissionRepositoryImpl extends QueryDslSupport implements PermissionRepositoryCustom {
    @Autowired
    public PermissionRepositoryImpl(EntityManager entityManager){
        super(Permission.class, entityManager);
    }

}
