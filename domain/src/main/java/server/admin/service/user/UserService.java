package server.admin.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.auth.dto.request.SignUpRequest;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.repository.BadgeRepository;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.user.dto.request.UserUpdateRequest;
import server.admin.model.user.entity.UserBadge;
import server.admin.model.user.entity.UserPolicyAgreement;
import server.admin.model.user.repository.UserPolicyAgreementRepository;
import server.admin.utils.page.PageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.User;
import server.admin.model.user.repository.UserBadgeRepository;
import server.admin.model.user.repository.UserRepository;

import java.util.ArrayList;
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

    private Boolean duplicatePhoneNumber( String phoneNumber ){
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        return optionalUser.isPresent();
    }

    public PageResult<UserProfileResponse> getAllUser(Pageable pageable){
        List<User> userList = userRepository.getAllUser(pageable);
        List<UserProfileResponse> userProfileResponses = new ArrayList<>();
        userList.forEach(user -> {
            UserProfileResponse userProfileResponse = UserProfileResponse.toBasicResponse(user);
            Optional<UserPolicyAgreement> optionalUserPolicyAgreement = userPolicyAgreementRepository.findByUserId(user.getId());
            List<BadgeResponse> badgeResponseList = userBadgeRepository.findByUser(user);
            userProfileResponse.setBadges(badgeResponseList);
            userProfileResponse.setPolicyAgreement(optionalUserPolicyAgreement.orElseThrow(UserPolicyAgreementNotExistException::new));
            userProfileResponses.add(userProfileResponse);
        });
        PageImpl<UserProfileResponse> pageResult = new PageImpl<>(userProfileResponses, Pageable.unpaged(), userProfileResponses.size());
        return new PageResult<>(pageResult);
    }

//    public PageResult<UserProfileResponse.Minified> searchUser(Pageable pageable, String nickname, Boolean isEnabled){
//        return new PageResult<>(userRepository.searchUser(pageable, nickname, isEnabled));
//    }

    public RestResponse createUser(SignUpRequest request){
        if(duplicatePhoneNumber(request.getPhoneNumber())){
            return RestFailResponse.newInstance(
                    HttpStatus.CONFLICT,
                    "해당 핸드폰 번호가 이미 존재합니다."
            );
        } else{
            User user = request.toEntity(request);
            userRepository.save(user);
            String message = "어드민 회원가입이 완료되었습니다.";
            return RestSuccessResponse.newInstance(
                    message
            );
        }
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
        User singleUser = request.setEntityExceptBadge(optionalUser.orElseThrow(UserNotExistException::new),request);
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
