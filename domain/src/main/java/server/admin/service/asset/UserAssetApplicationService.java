package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.UserAssetApplicationUpdateRequest;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.asset.repository.userAssetApplication.UserAssetApplicationRepository;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.exception.UserException;
import server.admin.model.user.repository.UserRepository;
import server.admin.utils.cursor.CursorResult;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static server.admin.model.asset.exception.UserAssetApplicationException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAssetApplicationService {
    private final UserAssetApplicationRepository userAssetApplicationRepository;
    private final UserRepository userRepository;

//    private Boolean hasNext(Long lastId) {
//        if (lastId == null) return false;
//        return userAssetApplicationRepository.existsByIdLessThan(lastId);
//    }

    @Transactional(readOnly = true)
    public UserAssetApplicationResponse getUserAssetApplication(Long userAssetApplicationId){
        final Optional<UserAssetApplication> optionalUserAssetApplication = userAssetApplicationRepository.findUserAssetApplicationById(userAssetApplicationId);//이부분 페치조인사용해서 가져오기로 수정하기->ok
        UserAssetApplication userAssetApplication = optionalUserAssetApplication.orElseThrow(UserAssetApplicationNotExistException::new);
        UserAssetApplicationResponse userAssetApplicationResponse = UserAssetApplicationResponse.toResponseExceptVerifier(userAssetApplication);
        final UserProfileResponse.Verifier verifier = userAssetApplication.getVerifierId() != null ? UserProfileResponse.Verifier.of(userRepository.findById(userAssetApplication.getVerifierId()).orElseThrow(UserException.UserNotExistException::new)) : null;
        userAssetApplicationResponse.setVerifier(verifier);
        return userAssetApplicationResponse;

    }

    @Transactional(readOnly = true)
    public PageResult<UserAssetApplicationResponse> getAllUserAssetApplication(
//            Long cursorId, Integer size, Boolean isVerified, Sort sort
    ){
        final List<UserAssetApplication> userAssetApplicationList = userAssetApplicationRepository.getUserAssetApplications(
//                cursorId, size, isVerified, sort
        );

        List<UserAssetApplicationResponse> userAssetApplicationResponseList = new ArrayList<>();
        userAssetApplicationList.forEach(userAssetApplication -> {
            UserAssetApplicationResponse userAssetApplicationResponse = UserAssetApplicationResponse.toResponseExceptVerifier(userAssetApplication);
            final UserProfileResponse.Verifier verifier = userAssetApplication.getVerifierId() != null ? UserProfileResponse.Verifier.of(userRepository.findById(userAssetApplication.getVerifierId()).orElseThrow(UserException.UserNotExistException::new)) : null;
            userAssetApplicationResponse.setVerifier(verifier);
            userAssetApplicationResponseList.add(userAssetApplicationResponse);
        });
//        if (userAssetApplicationResponseList.isEmpty()) throw new UserAssetApplicationNotExistException();
//        final int sizeOfPage  = userAssetApplicationResponseList.size();
//        final Long lastIdOfList = userAssetApplicationResponseList.get(userAssetApplicationResponseList.size()-1).getId();
        PageImpl<UserAssetApplicationResponse> pageResult = new PageImpl<>(userAssetApplicationResponseList, Pageable.unpaged(), userAssetApplicationResponseList.size());
//        return new CursorResult<>(userAssetApplicationResponseList, hasNext(lastIdOfList), lastIdOfList, sizeOfPage);
        return new PageResult<>(pageResult);
    }

    public UserAssetApplicationResponse updateUserAssetApplication(Long userAssetApplicationId, UserAssetApplicationUpdateRequest request){
        final Optional<UserAssetApplication> optional = userAssetApplicationRepository.findById(userAssetApplicationId);
            final UserAssetApplication userAssetApplication = UserAssetApplication.setBasicEntity(optional.orElseThrow(UserAssetApplicationNotExistException::new), request);
            UserAssetApplicationResponse userAssetApplicationResponse = UserAssetApplicationResponse.toResponseExceptVerifier(userAssetApplication);
            final UserProfileResponse.Verifier verifier = userAssetApplication.getVerifierId() != null ? UserProfileResponse.Verifier.of(userRepository.findById(userAssetApplication.getVerifierId()).orElseThrow(UserException.UserNotExistException::new)) : null;

            userAssetApplicationResponse.setVerifier(verifier);
            return userAssetApplicationResponse;

    }



}
