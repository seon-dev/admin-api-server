package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.asset.repository.UserAssetApplicationRepository;
import server.admin.model.brand.entity.Brand;
import server.admin.model.common.cursor.CursorResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAssetApplicationService {
    private final UserAssetApplicationRepository userAssetApplicationRepository;

    public UserAssetApplicationResponse getUserAssetApplication(Long userAssetApplicationId){
        Optional<UserAssetApplication> optionalUserAssetApplication = userAssetApplicationRepository.findById(userAssetApplicationId);
        return optionalUserAssetApplication.isPresent() ?
                UserAssetApplicationResponse.toResponse(optionalUserAssetApplication.get()) : null;
    }

//    public CursorResult<UserAssetApplicationResponse> getAllUserAssetApplication(){
//
//    }

    private Page<UserAssetApplication> getUserAssetApplications(Long cursorId, Pageable pageable){
        return cursorId == null ?
                userAssetApplicationRepository.findAllByOrderByIdAsc(pageable) : userAssetApplicationRepository.findByIdGreaterThanEqualOrderByIdAsc(cursorId,pageable);
    }

    private Boolean hasNext(Long cursordId) {
        if (cursordId == null) return false;
        return userAssetApplicationRepository.existsByIdGreaterThanEqual(cursordId);
    }

}
