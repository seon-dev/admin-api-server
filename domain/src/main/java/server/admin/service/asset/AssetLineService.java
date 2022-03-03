package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetLineCreateRequest;
import server.admin.model.asset.dto.request.AssetLineUpdateRequest;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.asset.exception.AssetBrandCategoryException;
import server.admin.model.asset.exception.AssetLineException;
import server.admin.model.asset.repository.assetBrandCategory.AssetBrandCategoryRepository;
import server.admin.model.asset.repository.assetLine.AssetLineRepository;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.brand.repository.BrandRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetLineService {
    private final AssetLineRepository assetLineRepository;
    private final BrandRepository brandRepository;
    private final AssetBrandCategoryRepository brandCategoryRepository;
    private final S3Service s3Service;


    @Transactional(readOnly = true)
    public AssetLineResponse getAssetLine(Long id){
        Optional<AssetLine> optionalAssetLine = assetLineRepository.findByIdFetchJoin(id);
        return AssetLineResponse.toResponse(optionalAssetLine.orElseThrow(AssetLineException.AssetLineNotExistException::new));
    }

    @Transactional(readOnly = true)
    public PageResult<AssetLineResponse> getAllAssetLine(){
        List<AssetLine> assetLineList = assetLineRepository.findAllFetchJoin();
        List<AssetLineResponse> assetLineResponseList = new ArrayList<>();
        assetLineList.forEach(assetLine -> {
            assetLineResponseList.add(AssetLineResponse.toResponse(assetLine));
        });
        PageImpl<AssetLineResponse> pageResult = new PageImpl<>(assetLineResponseList, Pageable.unpaged(), assetLineResponseList.size());
        return new PageResult(pageResult);
    }

    public AssetLineResponse createAssetLine(AssetLineCreateRequest request) throws Exception {
        AssetLine assetLine = new AssetLine();
        assetLine.setName(request.getName());
        assetLine.setIsEnabled(request.getIsEnabled());
        assetLine.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(BrandException.BrandNotExistException::new));
        assetLine.setBrandCategory(brandCategoryRepository.findById(request.getBrandCategoryId()).orElseThrow(AssetBrandCategoryException.AssetBrandCategoryNotExistException::new));
        s3Service.upload(request.getResourceUploaded(), request.getResourceFileName());
        assetLine.setResource(request.getResourceFileName());
        return AssetLineResponse.toResponse(assetLineRepository.save(assetLine));
    }

    public AssetLineResponse updateAssetLine(Long id, AssetLineUpdateRequest request) throws Exception {
        Optional<AssetLine> optionalAssetLine = assetLineRepository.findByIdFetchJoin(id);
        AssetLine assetLine = optionalAssetLine.orElseThrow(AssetLineException.AssetLineNotExistException::new);
        assetLine.setName(request.getName());
        s3Service.upload(request.getResourceUploaded(), request.getResourceFileName());
        assetLine.setResource(request.getResourceFileName());
        return AssetLineResponse.toResponse(assetLine);
    }

    public void deleteAssetLine(Long id){
        Optional<AssetLine> optionalAssetLine = assetLineRepository.findById(id);
        optionalAssetLine.orElseThrow(AssetLineException.AssetLineNotExistException::new).setIsEnabled(false);
    }
}
