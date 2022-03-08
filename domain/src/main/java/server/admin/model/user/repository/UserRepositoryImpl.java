package server.admin.model.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.QUser;
import server.admin.model.user.entity.User;
import server.admin.utils.page.PageResult;

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
    public List<User> getAllUser(Pageable pageable) {
        JPAQuery<?> query = queryFactory.selectFrom(user);
//                .where(
//                        checkEnabled(isEnabled)
//                );

//        List<Long> fetch = queryFactory.select(user.count())
//                .from(user)
//                .where(
//                        checkEnabled(isEnabled)
//                )
//                .fetch();
//        System.out.println(fetch);

        Long count = queryFactory.select(user.count())
                .from(user)
//                .where(
//                        checkEnabled(isEnabled)
//                )
                .fetchOne();

        List<User> userList = Objects.requireNonNull(getQuerydsl())
                .applyPagination(pageable, query)
                .select(user)
                .fetch();
        return userList;
//        return new PageImpl<>(userProfileResponses, pageable, count);

    }

    @Override
    public Page<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isEnabled){
        JPAQuery<?> query = queryFactory.from(user)
                .where(
                        user.nickname.containsIgnoreCase(nickname),
                        checkEnabled(isEnabled)
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


    private BooleanExpression checkEnabled(Boolean isEnabled){
        return isEnabled != null ? user.isEnabled.eq(isEnabled) : null;
    }
}
