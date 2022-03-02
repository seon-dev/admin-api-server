package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetQualityCreateRequest;
import server.admin.model.asset.dto.request.AssetQualityUpdateRequest;
import server.admin.model.asset.dto.response.AssetQualityResponse;
import server.admin.model.asset.dto.response.AssetSeasonResponse;
import server.admin.model.asset.entity.AssetQuality;
import server.admin.model.asset.exception.AssetQualityException;
import server.admin.model.asset.repository.AssetQualityRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetQualityService {
    private final AssetQualityRepository assetQualityRepository;

    public AssetQualityResponse getAssetQuality(Long id){
        Optional<AssetQuality> optionalAssetQuality = assetQualityRepository.findById(id);
        return AssetQualityResponse.toResponse(optionalAssetQuality.orElseThrow(AssetQualityException.AssetQualityNotExistException::new));
    }

    public PageResult<AssetQualityResponse> getAllAssetQuality(){
        List<AssetQuality> assetQualityList = assetQualityRepository.findAll();
        List<AssetQualityResponse> assetQualityResponseList = new ArrayList<>();
        assetQualityList.forEach(assetQuality -> {
            assetQualityResponseList.add(AssetQualityResponse.toResponse(assetQuality));
        });
        PageImpl<AssetQualityResponse> pageResult = new PageImpl(assetQualityResponseList, Pageable.unpaged(), assetQualityResponseList.size());
        return new PageResult<>(pageResult);
    }

    public AssetQualityResponse createAssetQuality(AssetQualityCreateRequest request){
        return AssetQualityResponse.toResponse(
                assetQualityRepository.save(request.toEntity())
        );
    }

    public AssetQualityResponse updateAssetQuality(Long assetQualityId, AssetQualityUpdateRequest request){
        Optional<AssetQuality> optionalAssetQuality = assetQualityRepository.findById(assetQualityId);

        return AssetQualityResponse.toResponse(
                request.setEntity(optionalAssetQuality.orElseThrow(AssetQualityException.AssetQualityNotExistException::new))
        );
    }
}
