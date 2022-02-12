package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.UserAssetApplicationUpdateRequest;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.asset.exception.UserAssetApplicationException;
import server.admin.model.asset.repository.UserAssetApplicationRepository;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.common.cursor.CursorResult;

import java.util.List;
import java.util.Optional;

import static server.admin.model.asset.exception.AssetPrototypeException.*;
import static server.admin.model.asset.exception.UserAssetApplicationException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAssetApplicationService {
    private final UserAssetApplicationRepository userAssetApplicationRepository;

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return userAssetApplicationRepository.existsByIdLessThan(lastId);
    }

    @Transactional(readOnly = true)
    public UserAssetApplicationResponse getUserAssetApplication(Long userAssetApplicationId){
        final Optional<UserAssetApplicationResponse> optionalUserAssetApplication = userAssetApplicationRepository.findResponseById(userAssetApplicationId);//이부분 페치조인사용해서 가져오기로 수정하기->ok
        optionalUserAssetApplication.orElseThrow(UserAssetApplicationNotExistException::new);
        return optionalUserAssetApplication.get();
    }

    @Transactional(readOnly = true)
    public CursorResult<List<UserAssetApplicationResponse>> getAllUserAssetApplication(Long cursorId, Integer size, Boolean isVerified, Sort sort){
        final List<UserAssetApplicationResponse> userAssetApplicationResponseList = userAssetApplicationRepository.getUserAssetApplications(cursorId, size, isVerified, sort);
        if (userAssetApplicationResponseList.isEmpty()) throw new UserAssetApplicationNotExistException();
        final int sizeOfPage  = userAssetApplicationResponseList.size();
        final Long lastIdOfList = userAssetApplicationResponseList.get(userAssetApplicationResponseList.size()-1).getId();

        return new CursorResult<>(userAssetApplicationResponseList, hasNext(lastIdOfList), lastIdOfList, sizeOfPage);
    }

    public UserAssetApplicationResponse updateUserAssetApplication(Long userAssetApplicationId, UserAssetApplicationUpdateRequest request){
        final Optional<UserAssetApplication> optional = userAssetApplicationRepository.findById(userAssetApplicationId);
        if (optional.isPresent()){
            final UserAssetApplication userAssetApplication = optional.get();
            userAssetApplication.setBasicEntity(request);
            return UserAssetApplicationResponse.toResponse(userAssetApplication);
        } else throw new UserAssetApplicationNotExistException();
    }



}
