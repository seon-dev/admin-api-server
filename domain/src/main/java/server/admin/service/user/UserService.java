package server.admin.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.admin.utils.page.PageResult;
import server.admin.model.user.dto.request.UserCreateRequest;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.User;
import server.admin.model.user.repository.UserBadgeRepository;
import server.admin.model.user.repository.UserRepository;

import java.util.Optional;

import static server.admin.model.user.exception.UserException.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private final UserBadgeRepository userBadgeRepository;

    public PageResult<UserProfileResponse.Minified> getAllUser(Pageable pageable, Boolean isVerified){
        return new PageResult<>(userRepository.getAllUser(pageable, isVerified), pageable);
    }

    public PageResult<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isVerified){
        return new PageResult<>(userRepository.searchUser(pageable, nickname, isVerified), pageable);
    }

//    public UserProfileResponse getUser(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
//        UserProfileResponse userProfileResponse = UserProfileResponse.toResponseWithoutBadge(user);
//        userProfileResponse.setBadges(userBadgeRepository.findByUser(user).orElseThrow(BadgeNotExistException::new));
//        return userProfileResponse;
//    }

//    public UserProfileResponse createUser(UserCreateRequest request){
//        validateDuplicatedUser(request);
//
//
//
//    }

//    private Boolean validateDuplicatedUser(UserCreateRequest request){
//        Optional<User> optionalUser = userRepository.findByNicknameAndPhoneNumber(
//                request.getNickname(), request.getPhoneNumber()
//        );
//        if (optionalUser.isEmpty()) return false;
//        else throw new UserDuplicatedException();
//    }
}
