package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.asset.repository.UserAssetApplicationRepository;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.common.cursor.CursorResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAssetApplicationService {
    private final UserAssetApplicationRepository userAssetApplicationRepository;

    private Page<UserAssetApplication> getUserAssetApplicationsWithPage(Long cursorId, Pageable pageable){
        return cursorId == null ? userAssetApplicationRepository.findAllByOrderByIdAsc(pageable): userAssetApplicationRepository.findByIdGreaterThanEqualOrderByIdAsc(cursorId, pageable);
    }

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return userAssetApplicationRepository.existsByIdGreaterThan(lastId);
    }

    @Transactional(readOnly = true)
    public UserAssetApplicationResponse getUserAssetApplication(Long userAssetApplicationId){
        Optional<UserAssetApplication> optionalUserAssetApplication = userAssetApplicationRepository.findById(userAssetApplicationId);
        optionalUserAssetApplication.orElseThrow(BrandException.BrandNotExistException::new);
        return UserAssetApplicationResponse.toResponse(optionalUserAssetApplication.get());
    }

    @Transactional(readOnly = true)
    public CursorResult<UserAssetApplicationResponse> getAllUserAssetApplication(Long cursorId, Pageable pageable){
        final Page<UserAssetApplication> allWithPagination = this.getUserAssetApplicationsWithPage(cursorId, pageable);
        final Page<UserAssetApplicationResponse> allDtoWithPagination = new PageImpl<>(allWithPagination
                .map(UserAssetApplicationResponse::toResponse)
                .toList());

        final List<UserAssetApplication> userAssetApplicationList = allWithPagination.getContent();
        final Long lastIdOfList = !allWithPagination.isEmpty() ? userAssetApplicationList.get(userAssetApplicationList.size()-1).getId() : null;

        return new CursorResult<>(allDtoWithPagination, hasNext(lastIdOfList));
    }



}
