package server.admin.service.styling;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.styling.dto.response.UserStylingResponse;
import server.admin.model.styling.entity.UserStyling;
import server.admin.model.styling.exception.UserStylingException;
import server.admin.model.styling.repository.UserStylingAssetReferenceRepository;
import server.admin.model.styling.repository.UserStylingRepository;
import server.admin.model.styling.repository.UserStylingResourceRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserStylingService {
    private final UserStylingRepository userStylingRepository;
    private final UserStylingAssetReferenceRepository userStylingAssetReferenceRepository;
    private final UserStylingResourceRepository userStylingResourceRepository;

    @Transactional(readOnly = true)
    public PageResult<UserStylingResponse> getAllUserStyling(){
        List<UserStyling> userStylingList = userStylingRepository.findAllFetchJoin();
        List<UserStylingResponse> userStylingResponseList = new ArrayList<>();
        userStylingList.forEach(userStyling -> {
            userStylingResponseList.add(UserStylingResponse.toResponse(userStyling));

        });

        userStylingResponseList.forEach(userStylingResponse -> {
            List<AssetPrototypeResponse.Minified> minifiedList = userStylingAssetReferenceRepository.findMinifiedByStylingId(userStylingResponse.getId());
            userStylingResponse.setReferenceAssets(minifiedList);
            List<String> resources = userStylingResourceRepository.findResourcesByStylingId(userStylingResponse.getId());
            userStylingResponse.setResources(resources);
        });

        PageImpl<UserStylingResponse> pageResult = new PageImpl<>(userStylingResponseList, Pageable.unpaged(), userStylingResponseList.size());
        return new PageResult(pageResult);
    }

    @Transactional(readOnly = true)
    public UserStylingResponse getUserStyling(Long stylingId){
        UserStyling userStyling = userStylingRepository.findByIdFetchJoin(stylingId);
        UserStylingResponse userStylingResponse = UserStylingResponse.toResponse(userStyling);
        List<AssetPrototypeResponse.Minified> minifiedList = userStylingAssetReferenceRepository.findMinifiedByStylingId(userStylingResponse.getId());
        List<String> resources = userStylingResourceRepository.findResourcesByStylingId(userStylingResponse.getId());
        userStylingResponse.setReferenceAssets(minifiedList);
        userStylingResponse.setResources(resources);
        return userStylingResponse;
    }

    public void deleteUserStyling(Long id){
        Optional<UserStyling> userStylingOptional = userStylingRepository.findById(id);
        userStylingOptional.orElseThrow(UserStylingException.UserStylingNotExistException::new).setIsEnabled(false);
    }

}
