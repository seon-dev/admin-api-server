package server.admin.model.permission.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.permission.entity.Permission;
import server.admin.model.permission.entity.QPermission;
import server.admin.model.user.entity.QUser;

import javax.persistence.EntityManager;
import java.util.List;

public class PermissionRepositoryImpl extends QueryDslSupport implements PermissionRepositoryCustom {
    @Autowired
    public PermissionRepositoryImpl(EntityManager entityManager){
        super(Permission.class, entityManager);
    }

    @Override
    public List<Permission> findAllFetchJoin() {
        return queryFactory.selectFrom(QPermission.permission)
                .where(
                        QPermission.permission.isEnabled.eq(true)
                )
                .leftJoin(QPermission.permission.user, QUser.user).fetchJoin()
                .fetch();
    }
}
