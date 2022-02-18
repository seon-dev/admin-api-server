package server.admin.model.user.repository;

import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepositoryCustom {
    List<BadgeResponse> findByUser(User user);

}
