package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import server.admin.model.asset.dto.request.AssetPrototypeCreateRequest;
import server.admin.model.asset.dto.request.AssetPrototypeUpdateRequest;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.asset.entity.*;
import server.admin.model.asset.exception.*;
import server.admin.model.asset.repository.*;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.exception.BrandException;
import server.admin.model.brand.repository.BrandRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetPrototypeService {
    private final AssetPrototypeRepository assetPrototypeRepository;
    private final AssetLineRepository assetLineRepository;
    private final AssetCollectionRepository assetCollectionRepository;
    private final AssetSeasonRepository assetSeasonRepository;
    private final BrandRepository brandRepository;
    private final AssetBrandCategoryRepository assetBrandCategoryRepository;

    private AssetLine findAssetLine(Long assetId){
        return assetId != null ? assetLineRepository.findById(assetId).orElseThrow(() -> new AssetLineException.AssetLineNotExistException()) : null;
    }
    private AssetCollection findAssetCollection(Long assetCollectionId){
        return assetCollectionId != null ? assetCollectionRepository.findById(assetCollectionId).orElseThrow(() -> new AssetCollectionException.AssetCollectionNotExistException()) : null;
    }
    private AssetSeason findAssetSeason(Long assetSeasonId){
        return assetSeasonId != null ? assetSeasonRepository.findById(assetSeasonId).orElseThrow(() -> new AssetLineException.AssetLineNotExistException()) : null;
    }
    private AssetBrandCategory findAssetBrandCategory(Long assetBrandCateogryId){
        return assetBrandCateogryId != null ? assetBrandCategoryRepository.findById(assetBrandCateogryId).orElseThrow(() -> new AssetBrandCategoryException.AssetBrandCategoryNotExistException()) : null;
    }
    private Brand findBrand(Long BrandId){
        return BrandId != null ? brandRepository.findById(BrandId).orElseThrow(() -> new BrandException.BrandNotExistException()) : null;
    }

    public AssetPrototypeResponse createAssetPrototype(AssetPrototypeCreateRequest request){
        AssetPrototype assetPrototype = AssetPrototypeCreateRequest.toEntity(request);
        assetPrototype.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(()-> new BrandException.BrandNotExistException()));
        assetPrototype.setCollection(assetCollectionRepository.findById(request.getAssetCollectionId()).orElseThrow(()-> new AssetCollectionException.AssetCollectionNotExistException()));
        assetPrototype.setSeason(assetSeasonRepository.findById(request.getAssetSeasonId()).orElseThrow(() -> new AssetSeasonException.AssetSeasonNotExistException()));
        assetPrototype.setLine(assetLineRepository.findById(request.getAssetLineId()).orElseThrow(()-> new AssetLineException.AssetLineNotExistException()));
        return AssetPrototypeResponse.toResponse(assetPrototypeRepository.save(assetPrototype));
    }

//    public Page<AssetPrototypeResponse> getAssetPrototype(){
//
//    }

    public AssetPrototypeResponse updateAssetPrototype(Long id, AssetPrototypeUpdateRequest request){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(id);
        optionalAssetPrototype.ifPresent(assetPrototype -> {
            AssetPrototype.setBasicEntity(assetPrototype,request);
            //setEntity부분 더티체킹 되는지 확인하기-> ok
            assetPrototype.setBrand(findBrand(request.getBrandId()));
            assetPrototype.setLine(findAssetLine(request.getAssetLineId()));
            assetPrototype.setSeason(findAssetSeason(request.getAssetSeasonId()));
            assetPrototype.setCollection(findAssetCollection(request.getAssetCollectionId()));
            assetPrototype.setBrandCategory(findAssetBrandCategory(request.getAssetBrandCategoryId()));
            assetPrototypeRepository.flush();
        });
        return AssetPrototypeResponse.toResponse(optionalAssetPrototype.get());
            //update된 애 제대로 나오는지 확인 -> ok

    }



    public void deleteAssetPrototype(Long assetId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(assetId);
        optionalAssetPrototype.ifPresentOrElse(
                assetPrototype -> assetPrototypeRepository.delete(assetPrototype),
                () -> { throw new AssetPrototypeException.AssetPrototypeNotExistException(); }
        );
    }
}
