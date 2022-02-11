package server.admin.service.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import static server.admin.model.asset.exception.AssetBrandCategoryException.*;
import static server.admin.model.asset.exception.AssetCollectionException.*;
import static server.admin.model.asset.exception.AssetLineException.*;
import static server.admin.model.asset.exception.AssetPrototypeException.*;
import static server.admin.model.brand.exception.BrandException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetPrototypeService {
    private final AssetPrototypeRepository assetPrototypeRepository;
    private final AssetLineRepository assetLineRepository;
    private final AssetCollectionRepository assetCollectionRepository;
    private final AssetSeasonRepository assetSeasonRepository;
    private final BrandRepository brandRepository;
    private final AssetBrandCategoryRepository assetBrandCategoryRepository;

    private AssetLine findAssetLine(Long assetId){
        return assetId != null ? assetLineRepository.findById(assetId).orElseThrow(AssetLineNotExistException::new) : null;
    }
    private AssetCollection findAssetCollection(Long assetCollectionId){
        return assetCollectionId != null ? assetCollectionRepository.findById(assetCollectionId).orElseThrow(AssetCollectionNotExistException::new) : null;
    }
    private AssetSeason findAssetSeason(Long assetSeasonId){
        return assetSeasonId != null ? assetSeasonRepository.findById(assetSeasonId).orElseThrow(AssetLineNotExistException::new) : null;
    }
    private AssetBrandCategory findAssetBrandCategory(Long assetBrandCateogryId){
        return assetBrandCateogryId != null ? assetBrandCategoryRepository.findById(assetBrandCateogryId).orElseThrow(AssetBrandCategoryNotExistException::new) : null;
    }
    private Brand findBrand(Long BrandId){
        return BrandId != null ? brandRepository.findById(BrandId).orElseThrow(BrandNotExistException::new) : null; //fetchjoin
    }



    public AssetPrototypeResponse createAssetPrototype(AssetPrototypeCreateRequest request){
        AssetPrototype assetPrototype = AssetPrototypeCreateRequest.toEntity(request);
        assetPrototype.setBrand(findBrand(request.getBrandId()));
        assetPrototype.setCollection(findAssetCollection(request.getAssetCollectionId()));
        assetPrototype.setSeason(findAssetSeason(request.getAssetSeasonId()));
        assetPrototype.setLine(findAssetLine(request.getAssetLineId()));
        System.out.println("insert쿼리확인하기");
        return AssetPrototypeResponse.toResponse(assetPrototypeRepository.save(assetPrototype));//여기는 잘 나옴
    }

    public AssetPrototypeResponse getAssetPrototype(Long assetPrototypeId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(assetPrototypeId);
        optionalAssetPrototype.orElseThrow(AssetPrototypeNotExistException::new);
        return AssetPrototypeResponse.toResponse(optionalAssetPrototype.get());//fetchjoin으로 겟한다음, response에 담기
    }


    public AssetPrototypeResponse updateAssetPrototype(Long id, AssetPrototypeUpdateRequest request){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findByIdWithFetchJoin(id);
        if( optionalAssetPrototype.isPresent()) {
            AssetPrototype assetPrototype = optionalAssetPrototype.get();
            AssetPrototype.setBasicEntity(assetPrototype,request);
            System.out.println("findBrand전");
            assetPrototype.setBrand(findBrand(request.getBrandId()));
            System.out.println("findBrand후");
            assetPrototype.setLine(findAssetLine(request.getAssetLineId()));
            assetPrototype.setSeason(findAssetSeason(request.getAssetSeasonId()));
            assetPrototype.setCollection(findAssetCollection(request.getAssetCollectionId()));
            assetPrototype.setBrandCategory(findAssetBrandCategory(request.getAssetBrandCategoryId()));
            System.out.println("findAssetBrandCategory후");
            assetPrototypeRepository.flush();
            return AssetPrototypeResponse.toResponse(assetPrototype);
            //이부분에서 나는 프록시객체오류인듯, **toresponse에 get으로 객체에 접근해서 넣기->해도 안됨->페치조인으로 해결함**
        } else throw new AssetPrototypeNotExistException();
//        optionalAssetPrototype.ifPresent(assetPrototype -> {
//            AssetPrototype.setBasicEntity(assetPrototype,request);
//            //setEntity부분 더티체킹 되는지 확인하기-> ok
//            assetPrototype.setBrand(findBrand(request.getBrandId()));
//            assetPrototype.setLine(findAssetLine(request.getAssetLineId()));
//            assetPrototype.setSeason(findAssetSeason(request.getAssetSeasonId()));
//            assetPrototype.setCollection(findAssetCollection(request.getAssetCollectionId()));
//            assetPrototype.setBrandCategory(findAssetBrandCategory(request.getAssetBrandCategoryId()));
//            assetPrototypeRepository.flush();
//        });
//        return AssetPrototypeResponse.toResponse(assetPrototypeRepository.save(optionalAssetPrototype.get()));//이부분에서 나는 프록시객체오류인듯
            //update된 애 제대로 나오는지 확인 -> ok

    }



    public void deleteAssetPrototype(Long assetId){
        Optional<AssetPrototype> optionalAssetPrototype = assetPrototypeRepository.findById(assetId);
        optionalAssetPrototype.ifPresentOrElse(
                assetPrototype -> { assetPrototype.setIsEnabled(false);},
                () -> {
                    throw new AssetPrototypeNotExistException();
                }
        );
    }
}
