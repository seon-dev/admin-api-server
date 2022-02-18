package server.admin.model.user.repository;

import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.user.entity.QUser;
import server.admin.model.user.entity.User;
import server.admin.model.user.entity.UserBadge;

import javax.persistence.EntityManager;
import java.util.List;

import static server.admin.model.badge.entity.QBadge.*;
import static server.admin.model.user.entity.QUserBadge.*;

public class UserBadgeRepositoryImpl extends QueryDslSupport implements UserBadgeRepositoryCustom {

    @Autowired
    public UserBadgeRepositoryImpl(EntityManager entityManager) {
        super(UserBadge.class, entityManager);
    }


    @Override
    public List<BadgeResponse> findByUser(User user) {
        List<BadgeResponse> responseList = queryFactory.from(userBadge)
                .leftJoin(userBadge.badge, badge)
                .leftJoin(userBadge.user, QUser.user)
                .where(
                        QUser.user.id.eq(user.getId())
                )
                .select(
                        Projections.constructor(BadgeResponse.class,
                                badge.id,
                                badge.name,
                                badge.description,
                                badge.resource,
                                badge.isEnabled
                        )
                )
                .fetch();
        return responseList;
    }


}
