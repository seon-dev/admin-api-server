package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssetPrototypeCreateRequest {
    @NotBlank
    private String name;
    private String decorator;
    private String code;
    private String description;
    @NotNull
    private Long releasePrice;
    private String additional;
//    private Integer trendy;
    private String keywords;
    @NotNull
    private MultipartFile resourceFront;
    private MultipartFile resourceRear;
    private MultipartFile resourceSide;
    @NotNull
    private MultipartFile resourceAdditional;
    @NotNull
    private Boolean isEnabled;
    private Long brandId;
    private Long assetLineId;
    private Long assetSeasonId;
//    private Long assetCollectionId;
    private Long assetBrandCategoryId;

    public static AssetPrototype toEntityExcept(AssetPrototypeCreateRequest request){
        AssetPrototype assetPrototype = new AssetPrototype();
        if(request.getName()!=null) assetPrototype.setName(request.getName());
        if(request.getDecorator() !=null) assetPrototype.setDecorator(request.getDecorator());
        if(request.getCode() !=null) assetPrototype.setCode(request.getCode());
        if(request.getDescription() !=null) assetPrototype.setDescription(request.getDescription());
        if(request.getReleasePrice() !=null) assetPrototype.setReleasePrice(request.getReleasePrice());
//        assetPrototype.setCurrentPrice(request.getCurrentPrice());
//        assetPrototype.setResourceAdditional(request.getResourceAdditional());
//        assetPrototype.setResourceSide(request.getResourceSide());
//        assetPrototype.setResourceFront(request.getResourceFront());
//        assetPrototype.setResourceRear(request.getResourceRear());
        if(request.getAdditional() != null) assetPrototype.setAdditional(request.getAdditional());
//        assetPrototype.setTrendy(request.getTrendy());
        if(request.getKeywords() != null) assetPrototype.setKeywords(request.getKeywords());
        return assetPrototype;
    }
}

