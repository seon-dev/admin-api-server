package server.admin.model.styling.repository;

import server.admin.model.styling.entity.UserStyling;

import java.util.List;

public interface UserStylingRepositoryCustom  {
    List<UserStyling> findAllFetchJoin();
    UserStyling findByIdFetchJoin(Long id);
}
