package server.admin.model.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getAllUser(Pageable pageable);
    Page<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isEnabled);
}
