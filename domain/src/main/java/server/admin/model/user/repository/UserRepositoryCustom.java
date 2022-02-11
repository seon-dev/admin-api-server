package server.admin.model.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.user.dto.response.UserProfileResponse;

import java.util.List;

public interface UserRepositoryCustom {
    Page<UserProfileResponse.Minified> getAllUser(Pageable pageable, Boolean isVerified);
    Page<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isVerified);
}
