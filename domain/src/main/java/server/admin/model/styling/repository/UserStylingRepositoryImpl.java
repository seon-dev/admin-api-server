package server.admin.model.styling.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.styling.entity.QUserStyling;
import server.admin.model.styling.entity.UserStyling;
import server.admin.model.user.entity.QUser;

import javax.persistence.EntityManager;
import java.util.List;

public class UserStylingRepositoryImpl extends QueryDslSupport implements UserStylingRepositoryCustom {

    @Autowired
    public UserStylingRepositoryImpl(EntityManager entityManager) { super(UserStyling.class, entityManager); }


    @Override
    public List<UserStyling> findAllFetchJoin() {
        return queryFactory.selectFrom(QUserStyling.userStyling)
                .leftJoin(QUserStyling.userStyling.user, QUser.user).fetchJoin()
                .fetch();
    }

    @Override
    public UserStyling findByIdFetchJoin(Long id) {
        return queryFactory.selectFrom(QUserStyling.userStyling)
                .leftJoin(QUserStyling.userStyling.user, QUser.user).fetchJoin()
                .where(QUserStyling.userStyling.id.eq(id))
                .fetchOne();
    }


}
