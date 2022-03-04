package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
    private String resourceFrontUploaded;
    @NotNull
    private String resourceFrontExtension;
    private String resourceRearUploaded;
    private String resourceRearExtension;

    private String resourceSideUploaded;
    private String resourceSideExtension;

    @NotNull
    private String resourceAdditionalUploaded;
    @NotNull
    private String resourceAdditionalExtension;

    @NotNull
    private Boolean isEnabled;
    @NotNull
    private Long brandId;
    private Long assetLineId;
    private Long assetSeasonId;
//    private Long assetCollectionId;
    private Long assetBrandCategoryId;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName(String postfix) {
        return uid + "_" + postfix + resourceFrontExtension;
    }

    public static AssetPrototype toEntityExcept(AssetPrototypeCreateRequest request){
        AssetPrototype assetPrototype = new AssetPrototype();
        if(request.getName()!=null) assetPrototype.setName(request.getName());
        if(request.getDecorator() !=null) assetPrototype.setDecorator(request.getDecorator());
        if(request.getCode() !=null) assetPrototype.setCode(request.getCode());
        if(request.getDescription() !=null) assetPrototype.setDescription(request.getDescription());
        if(request.getReleasePrice() !=null) assetPrototype.setReleasePrice(request.getReleasePrice());
        if(request.getAdditional() != null) assetPrototype.setAdditional(request.getAdditional());
        if(request.getKeywords() != null) assetPrototype.setKeywords(request.getKeywords());
        if(request.getResourceAdditionalUploaded() != null && request.getResourceAdditionalExtension() != null) assetPrototype.setResourceAdditional(request.getResourceFileName("additional"));
        if(request.getResourceFrontUploaded() != null && request.getResourceFrontExtension() != null) assetPrototype.setResourceFront(request.getResourceFileName("front"));
        if(request.getResourceRearUploaded() != null && request.getResourceRearExtension() != null ) assetPrototype.setResourceRear(request.getResourceFileName("rear"));
        if(request.getResourceSideUploaded() != null && request.getResourceSideExtension() != null) assetPrototype.setResourceSide(request.getResourceFileName("side"));
        return assetPrototype;
    }
}

