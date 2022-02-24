package server.admin.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.repository.BadgeRepository;
import server.admin.model.user.dto.request.UserUpdateRequest;
import server.admin.model.user.entity.UserBadge;
import server.admin.model.user.entity.UserPolicyAgreement;
import server.admin.model.user.repository.UserPolicyAgreementRepository;
import server.admin.utils.page.PageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.User;
import server.admin.model.user.repository.UserBadgeRepository;
import server.admin.model.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static server.admin.model.user.exception.UserException.*;
import static server.admin.model.user.exception.UserPolicyAgreementException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserPolicyAgreementRepository userPolicyAgreementRepository;
    private final BadgeRepository badgeRepository;

    public PageResult<UserProfileResponse.Minified> getAllUser(Pageable pageable, Boolean isEnabled){
        return new PageResult<>(userRepository.getAllUser(pageable, isEnabled));
    }

    public PageResult<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isEnabled){
        return new PageResult<>(userRepository.searchUser(pageable, nickname, isEnabled));
    }

    public UserProfileResponse getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
        UserProfileResponse userProfileResponse = UserProfileResponse.toBasicResponse(user);
        userProfileResponse.setBadges(userBadgeRepository.findByUser(user));
        userProfileResponse.setPolicyAgreement(userPolicyAgreementRepository.findByUserId(userId).orElseThrow(UserPolicyAgreementNotExistException::new));
        return userProfileResponse;
    }

    public UserProfileResponse updateUser(Long userId, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        //존재하는 유저 엔티티 set하기
        User singleUser = request.setEntityExceptBadge(optionalUser.orElseThrow(UserNotExistException::new));
        //badge정보 업데이트하기
        request.getBadges().forEach(badgeId -> {
            Badge badge = badgeRepository.findBadgeById(badgeId);
            Optional<UserBadge> userBadgeOptional = userBadgeRepository.findByBadge(badge);
            if( !userBadgeOptional.isPresent() ){
                UserBadge userBadge = new UserBadge();
                userBadge.setBadge(badge);
                userBadge.setUser(singleUser);
                userBadge.setIsEnabled(true);
                userBadge.setIsPublic(true);
                userBadgeRepository.save(userBadge);
            } else if(!userBadgeOptional.get().getBadge().getId().equals(badgeId)){
                userBadgeOptional.get().setBadge(badge);
            }
        });
        //response로 바꾸기
        UserProfileResponse userProfileResponse = UserProfileResponse.toBasicResponse(singleUser);
        List<BadgeResponse> badgeResponses = userBadgeRepository.findByUser(singleUser);
        //response에 badgeresponse set하기
        userProfileResponse.setBadges( badgeResponses );
        //response에 policyAgreement set하기
        Optional<UserPolicyAgreement> optionalUserPolicyAgreement = userPolicyAgreementRepository.findByUserId(singleUser.getId());
        userProfileResponse.setPolicyAgreement(optionalUserPolicyAgreement.orElseThrow(UserPolicyAgreementNotExistException::new));
        return userProfileResponse;
    }

    public String deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(UserNotExistException::new).setIsEnabled(false);
        String message = "유저 정보를 삭제하였습니다.";
        return message;
    }

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
