package server.admin.model.styling.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.styling.entity.QUserStylingComment;
import server.admin.model.styling.entity.UserStylingComment;

import javax.persistence.EntityManager;
import java.util.List;

public class UserStylingCommentRepositoryImpl extends QueryDslSupport implements UserStylingCommentRepositoryCustom {

    @Autowired
    public UserStylingCommentRepositoryImpl(EntityManager entityManager){
        super(UserStylingComment.class, entityManager);
    }


    @Override
    public List<UserStylingComment> findAllByStylingId(Long id) {
        return queryFactory.selectFrom(QUserStylingComment.userStylingComment1)
                .leftJoin(QUserStylingComment.userStylingComment1)
                .on(QUserStylingComment.userStylingComment1.id.eq(QUserStylingComment.userStylingComment1.id))
                .where(
                        QUserStylingComment.userStylingComment1.styling.id.eq(id)
                        )
                .groupBy(QUserStylingComment.userStylingComment1.id)
                .fetch();
    }

    @Override
    public List<UserStylingComment> findByUserStylingCommentId(Long id) {
        return queryFactory.selectFrom(QUserStylingComment.userStylingComment1)
                .where(
                        QUserStylingComment.userStylingComment1.userStylingComment.id.eq(id)
                )
                .fetch();
    }

}
