package server.admin.model.styling.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.styling.entity.QUserStylingResource;
import server.admin.model.styling.entity.UserStylingResource;

import javax.persistence.EntityManager;
import java.util.List;

public class UserStylingResourceRepositoryImpl extends QueryDslSupport implements UserStylingResourceRepositoryCustom {
    @Autowired
    public UserStylingResourceRepositoryImpl(EntityManager entityManager){
        super(UserStylingResource.class, entityManager);
    }


    @Override
    public List<String> findResourcesByStylingId(Long id) {
        return queryFactory.select(QUserStylingResource.userStylingResource.resource)
                .from(QUserStylingResource.userStylingResource)
                .where(QUserStylingResource.userStylingResource.userStyling.id.eq(id))
                .fetch();
    }

}
