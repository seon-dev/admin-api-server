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
        //resource upload
        assetPrototype.setResourceFront(s3Service.upload(request.getResourceFront()));
        if(request.getResourceRear() != null) assetPrototype.setResourceRear(s3Service.upload(request.getResourceRear()));
        assetPrototype.setResourceAdditional(s3Service.upload(request.getResourceAdditional()));
        if(request.getResourceSide() != null) assetPrototype.setResourceSide(s3Service.upload(request.getResourceSide()));
        //ManyToOne relation
        assetPrototype.setBrand(brandRepository.findBrandById(request.getBrandId()));
        assetPrototype.setLine(assetLineRepository.findLineById(request.getAssetLineId()));
        assetPrototype.setSeason(assetSeasonRepository.findSeasonById(request.getAssetSeasonId()));
        assetPrototype.setBrandCategory(assetBrandCategoryRepository.findBrandCategoryById(request.getAssetBrandCategoryId()));

        return AssetPrototypeResponse.toResponse(assetPrototypeRepository.save(assetPrototype));//여기는 잘 나옴
    }

    @Transactional(readOnly = true)
    public AssetPrototypeResponse getAssetPrototype(Long assetPrototypeId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findByIdWithFetchJoin(assetPrototypeId);
        return AssetPrototypeResponse.toResponse(optionalAssetPrototype.orElseThrow(AssetPrototypeNotExistException::new));
        //fetchjoin으로 겟한다음, response에 담기
    }

    @Transactional(readOnly = true)
    public PageResult<AssetPrototypeResponse> getAllAssetPrototype(Pageable pageable, Boolean isEnabled){
        return new PageResult<>(assetPrototypeRepository.getAllAssetPrototype(pageable, isEnabled));
    }

    public AssetPrototypeResponse updateAssetPrototype(Long id, AssetPrototypeUpdateRequest request) throws IOException {
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findByIdWithFetchJoin(id);
        if( optionalAssetPrototype.isPresent()) {
            AssetPrototype assetPrototype = request.toEntityExcept(optionalAssetPrototype.get());
            if(request.getResourceFront() != null) assetPrototype.setResourceFront(s3Service.upload(request.getResourceFront()));
            if(request.getResourceAdditional() != null) assetPrototype.setResourceAdditional(s3Service.upload(request.getResourceAdditional()));
            if(request.getResourceRear() != null) assetPrototype.setResourceRear(s3Service.upload(request.getResourceRear()));
            if(request.getResourceSide() != null) assetPrototype.setResourceSide(s3Service.upload(request.getResourceSide()));


            assetPrototype.setBrand(brandRepository.findBrandById(request.getBrandId()));
            assetPrototype.setLine(assetLineRepository.findLineById(request.getAssetLineId()));
            assetPrototype.setSeason(assetSeasonRepository.findSeasonById(request.getAssetSeasonId()));
            assetPrototype.setBrandCategory(assetBrandCategoryRepository.findBrandCategoryById(request.getAssetBrandCategoryId()));
            return AssetPrototypeResponse.toResponse(assetPrototype);
        } else throw new AssetPrototypeNotExistException();

    }
//
//

    public void deleteAssetPrototype(Long assetPrototypeId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(assetPrototypeId);
        optionalAssetPrototype.ifPresent(
                assetPrototype -> { assetPrototype.setIsEnabled(false);}
                    );
        optionalAssetPrototype.orElseThrow(AssetPrototypeNotExistException::new);
    }
}
