package server.admin.model.styling.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.styling.entity.UserStylingComment;

import java.util.List;

public interface UserStylingCommentRepository extends JpaRepository<UserStylingComment, Long>, UserStylingCommentRepositoryCustom {
}
