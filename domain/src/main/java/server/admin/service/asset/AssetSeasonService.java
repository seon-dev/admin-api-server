package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetLineCreateRequest;
import server.admin.model.asset.dto.request.AssetLineUpdateRequest;
import server.admin.model.asset.dto.request.AssetSeasonCreateRequest;
import server.admin.model.asset.dto.request.AssetSeasonUpdateRequest;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.dto.response.AssetSeasonResponse;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.asset.entity.AssetSeason;
import server.admin.model.asset.exception.AssetBrandCategoryException;
import server.admin.model.asset.exception.AssetLineException;
import server.admin.model.asset.exception.AssetSeasonException;
import server.admin.model.asset.repository.AssetSeasonRepository;
import server.admin.model.brand.exception.BrandException;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetSeasonService {
    private final AssetSeasonRepository assetSeasonRepository;
    private final S3Service s3Service;


    @Transactional(readOnly = true)
    public AssetSeasonResponse getAssetSeason(Long id){
        Optional<AssetSeason> optionalAssetSeason = assetSeasonRepository.findById(id);
        return AssetSeasonResponse.toResponse(optionalAssetSeason.orElseThrow(AssetSeasonException.AssetSeasonNotExistException::new));
    }

    @Transactional(readOnly = true)
    public PageResult<AssetSeasonResponse> getAllAssetSeason(){
        List<AssetSeason> all = assetSeasonRepository.findAll();
        List<AssetSeasonResponse> assetSeasonResponseList = new ArrayList<>();
        all.forEach(assetSeason -> {
            assetSeasonResponseList.add(AssetSeasonResponse.toResponse(assetSeason));
        });
        PageImpl<AssetSeasonResponse> pageResult = new PageImpl<>(assetSeasonResponseList, Pageable.unpaged(), assetSeasonResponseList.size());
        return new PageResult(pageResult);
    }

    public AssetSeasonResponse createAssetSeason(AssetSeasonCreateRequest request) throws Exception {
        AssetSeason assetSeason = new AssetSeason();
        assetSeason.setName(request.getName());
        assetSeason.setIsEnabled(request.getIsEnabled());
        s3Service.upload(request.getResourceUploaded(), request.getResourceFileName());
        assetSeason.setResource(request.getResourceFileName());
        return AssetSeasonResponse.toResponse(assetSeasonRepository.save(assetSeason));
    }

    public AssetSeasonResponse updateAssetSeason(Long id, AssetSeasonUpdateRequest request) throws Exception {
        Optional<AssetSeason> optionalAssetSeason = assetSeasonRepository.findById(id);
        AssetSeason assetSeason = optionalAssetSeason.orElseThrow(AssetSeasonException.AssetSeasonNotExistException::new);
        assetSeason.setName(request.getName());
        s3Service.upload(request.getResourceUploaded(),request.getResourceFileName());
        assetSeason.setResource(request.getResourceFileName());
        return AssetSeasonResponse.toResponse(assetSeason);
    }

    public void deleteAssetSeason(Long id){
        Optional<AssetSeason> optionalAssetSeason = assetSeasonRepository.findById(id);
        optionalAssetSeason.orElseThrow(AssetSeasonException.AssetSeasonNotExistException::new).setIsEnabled(false);
    }
}
