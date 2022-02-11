package server.admin.model.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.entity.QBadge;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.common.page.CustomPageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.QUser;
import server.admin.model.user.entity.QUserBadge;
import server.admin.model.user.entity.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static server.admin.model.user.entity.QUser.*;

public class UserRepositoryImpl extends QueryDslSupport implements UserRepositoryCustom {

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager){
        super(User.class, entityManager);
    }
    @Override
    public Page<UserProfileResponse.Minified> getAllUser(Pageable pageable, Boolean isVerified) {
        JPAQuery<?> query = queryFactory.selectFrom(user)
                .where(
                        checkVerified(isVerified)
                );

        final Long count = queryFactory.select(user.count())
                .from(user)
                .where(
                        checkVerified(isVerified)
                )
                .fetchOne();

        List<UserProfileResponse.Minified> userProfileResponses = Objects.requireNonNull(getQuerydsl())
                .applyPagination(pageable, query)
                .select(Projections.constructor(UserProfileResponse.Minified.class,
                        user.id,
                        user.nickname,
                        user.resource
                        ))
                .fetch();

        return new PageImpl<>(userProfileResponses, pageable, count);

    }

    @Override
    public Page<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isVerified){
        JPAQuery<?> query = queryFactory.from(user)
                .where(
                        user.nickname.containsIgnoreCase(nickname),
                        checkVerified(isVerified)
                );
        final Long count = query.select(user.count()).fetchOne();

        List<UserProfileResponse.Minified> userProfileResponses = query
                .select(
                        Projections.constructor(UserProfileResponse.Minified.class,
                                user.id,
                                user.nickname,
                                user.resource
                        )
                ).fetch();

        return new PageImpl<>(userProfileResponses,pageable, count);
    }


    private BooleanExpression checkVerified(Boolean isVerified){
        return isVerified != null ? user.isEnabled.eq(isVerified) : null;
    }
}
