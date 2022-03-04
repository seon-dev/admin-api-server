package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.asset.dto.request.AssetPrototypeCreateRequest;
import server.admin.model.asset.dto.request.AssetPrototypeUpdateRequest;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.asset.entity.*;
import server.admin.model.asset.repository.*;
import server.admin.model.asset.repository.assetBrandCategory.AssetBrandCategoryRepository;
import server.admin.model.asset.repository.assetLine.AssetLineRepository;
import server.admin.model.asset.repository.assetPrototype.AssetPrototypeRepository;
import server.admin.model.brand.repository.BrandRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.io.IOException;
import java.util.Optional;


import static server.admin.model.asset.exception.AssetPrototypeException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetPrototypeService {
    private final AssetPrototypeRepository assetPrototypeRepository;
    private final AssetLineRepository assetLineRepository;
    private final AssetSeasonRepository assetSeasonRepository;
    private final BrandRepository brandRepository;
    private final AssetBrandCategoryRepository assetBrandCategoryRepository;
    private final S3Service s3Service;


    public AssetPrototypeResponse createAssetPrototype(AssetPrototypeCreateRequest request) throws IOException {
        AssetPrototype assetPrototype = AssetPrototypeCreateRequest.toEntityExcept(request);
        //ManyToOne relation
        assetPrototype.setBrand(brandRepository.findBrandById(request.getBrandId()));
        if(request.getAssetLineId() != null) assetPrototype.setLine(assetLineRepository.findLineById(request.getAssetLineId()));
        if(request.getAssetSeasonId() != null) assetPrototype.setSeason(assetSeasonRepository.findSeasonById(request.getAssetSeasonId()));
        if(request.getAssetBrandCategoryId() != null) assetPrototype.setBrandCategory(assetBrandCategoryRepository.findBrandCategoryById(request.getAssetBrandCategoryId()));

        return AssetPrototypeResponse.toResponse(assetPrototypeRepository.save(assetPrototype));//여기는 잘 나옴
    }

    @Transactional(readOnly = true)
    public AssetPrototypeResponse getAssetPrototype(Long assetPrototypeId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findByIdWithFetchJoin(assetPrototypeId);
        return AssetPrototypeResponse.toResponse(optionalAssetPrototype.orElseThrow(AssetPrototypeNotExistException::new));
    }

    @Transactional(readOnly = true)
    public PageResult<AssetPrototypeResponse> getAllAssetPrototype(Pageable pageable){
        return new PageResult<>(assetPrototypeRepository.getAllAssetPrototype(pageable));
    }

    public AssetPrototypeResponse updateAssetPrototype(Long id, AssetPrototypeUpdateRequest request) throws Exception {
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findByIdWithFetchJoin(id);
        if( optionalAssetPrototype.isPresent()) {
            AssetPrototype assetPrototype = request.setEntityExcept(optionalAssetPrototype.get(), request);
            if(request.getResourceAdditionalUploaded() != null && request.getResourceAdditionalExtension() != null) {
                final String filename = request.getResourceFileName("additional");
                s3Service.upload(request.getResourceAdditionalUploaded(), filename);
                assetPrototype.setResourceAdditional(filename);
            }
            if(request.getResourceFrontUploaded() != null && request.getResourceFrontExtension() != null) {
                final String filename = request.getResourceFileName("front");
                s3Service.upload(request.getResourceFrontUploaded(), filename);
                assetPrototype.setResourceFront(filename);            }
            if(request.getResourceRearUploaded() != null && request.getResourceRearExtension() != null ) {
                final String filename = request.getResourceFileName("rear");
                s3Service.upload(request.getResourceRearUploaded(), filename);
                assetPrototype.setResourceRear(filename);            }
            if(request.getResourceSideUploaded() != null && request.getResourceSideExtension() != null) {
                final String filename = request.getResourceFileName("side");
                s3Service.upload(request.getResourceSideUploaded(), filename);
                assetPrototype.setResourceSide(filename);            }

            if(request.getBrandId() != null) assetPrototype.setBrand(brandRepository.findBrandById(request.getBrandId()));
            if(request.getAssetLineId() != null)assetPrototype.setLine(assetLineRepository.findLineById(request.getAssetLineId()));
            if(request.getAssetSeasonId() != null)assetPrototype.setSeason(assetSeasonRepository.findSeasonById(request.getAssetSeasonId()));
            if(request.getAssetBrandCategoryId() != null)assetPrototype.setBrandCategory(assetBrandCategoryRepository.findBrandCategoryById(request.getAssetBrandCategoryId()));
            return AssetPrototypeResponse.toResponse(assetPrototype);
        } else throw new AssetPrototypeNotExistException();

    }
//
//

    public void deleteAssetPrototype(Long assetPrototypeId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(assetPrototypeId);
        optionalAssetPrototype.orElseThrow(AssetPrototypeNotExistException::new);
        optionalAssetPrototype.ifPresent(
                assetPrototype -> { assetPrototype.setIsEnabled(false);}
                    );
    }
}
