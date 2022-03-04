package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
//https://kys9261.github.io/2021/10/13/programming/java/gson-exclude-field/
public class AssetPrototypeUpdateRequest {
    private String name;
    private String decorator;
    private String code;
    private String description;
    private Long releasePrice;
    private String additional;
    //    private Integer trendy;
    private String keywords;
    private String resourceFrontUploaded;
    private String resourceFrontExtension;
    private String resourceRearUploaded;
    private String resourceRearExtension;
    private String resourceSideUploaded;
    private String resourceSideExtension;
    private String resourceAdditionalUploaded;
    private String resourceAdditionalExtension;
    private Boolean isEnabled;
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

    public static AssetPrototype setEntityExcept(AssetPrototype entity, AssetPrototypeUpdateRequest request){
        if(request.getName()!=null) entity.setName(request.getName());
        if(request.getDecorator()!=null) entity.setDecorator(request.getDecorator());
        if(request.getCode()!=null) entity.setCode(request.getCode());
        if(request.getDescription()!=null) entity.setDescription(request.getDescription());
        if(request.getReleasePrice()!=null) entity.setReleasePrice(request.getReleasePrice());
        if(request.getAdditional()!=null) entity.setAdditional(request.getAdditional());
        if(request.getKeywords()!=null) entity.setKeywords(request.getKeywords());
        if(request.getIsEnabled()!=null) entity.setIsEnabled(request.getIsEnabled());
        if(request.getResourceAdditionalUploaded() != null && request.getResourceAdditionalExtension() != null) entity.setResourceAdditional(request.getResourceFileName("additional"));
        if(request.getResourceFrontUploaded() != null && request.getResourceFrontExtension() != null) entity.setResourceFront(request.getResourceFileName("front"));
        if(request.getResourceRearUploaded() != null && request.getResourceRearExtension() != null ) entity.setResourceRear(request.getResourceFileName("rear"));
        if(request.getResourceSideUploaded() != null && request.getResourceSideExtension() != null) entity.setResourceSide(request.getResourceFileName("side"));
        return entity;
    }
}
