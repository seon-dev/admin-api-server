package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.Setter;
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
    private long releasePrice;
    @NotNull
    private long currentPrice;
    private String additional;
    private Integer trendy;
    private String keywords;
    private String resourceFront;
    private String resourceRear;
    private String resourceSide;
    private String resourceAdditional;
    @NotNull
    private Long brandId;
    private Long assetLineId;
    private Long assetSeasonId;
    private Long assetCollectionId;
    @NotNull
    private Long assetBrandCategoryId;

    public static AssetPrototype toEntity(AssetPrototypeCreateRequest request){
        AssetPrototype assetPrototype = new AssetPrototype();
        assetPrototype.setName(request.getName());
        assetPrototype.setDecorator(request.getDecorator());
        assetPrototype.setCode(request.getCode());
        assetPrototype.setDescription(request.getDescription());
        assetPrototype.setReleasePrice(request.getReleasePrice());
        assetPrototype.setCurrentPrice(request.getCurrentPrice());
        assetPrototype.setResourceAdditional(request.getResourceAdditional());
        assetPrototype.setResourceSide(request.getResourceSide());
        assetPrototype.setResourceFront(request.getResourceFront());
        assetPrototype.setResourceRear(request.getResourceRear());
        assetPrototype.setAdditional(request.getAdditional());
        assetPrototype.setTrendy(request.getTrendy());
        assetPrototype.setKeywords(request.getKeywords());
        return assetPrototype;
    }
}
