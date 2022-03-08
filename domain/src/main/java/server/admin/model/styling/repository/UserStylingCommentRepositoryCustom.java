package server.admin.model.styling.repository;

import server.admin.model.styling.entity.UserStylingComment;

import java.util.List;

public interface UserStylingCommentRepositoryCustom {
    List<UserStylingComment> findAllByStylingId(Long id);
    List<UserStylingComment> findByUserStylingCommentId(Long id);

}
