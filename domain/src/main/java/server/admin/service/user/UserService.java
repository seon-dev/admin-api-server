package server.admin.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.admin.model.common.page.CustomPageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public CustomPageResult<UserProfileResponse.Minified> getAllUser(Pageable pageable, Boolean isVerified){
        return new CustomPageResult<>(userRepository.getAllUser(pageable, isVerified), pageable);
    }

    public CustomPageResult<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isVerified){
        return new CustomPageResult<>(userRepository.searchUser(pageable, nickname, isVerified), pageable);
    }

}
