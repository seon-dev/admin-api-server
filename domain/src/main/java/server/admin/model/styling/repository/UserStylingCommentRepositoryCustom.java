package server.admin.model.styling.repository;

import server.admin.model.styling.entity.UserStylingComment;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserStylingCommentRepositoryCustom {
    List<UserStylingComment> findAllByStylingId(Long id);
    List<UserStylingComment> findByUserStylingCommentId(Long id);
    Optional<UserStylingComment> findByIdFetchJoin(Long id);

}
