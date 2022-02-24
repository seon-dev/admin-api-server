package server.admin.model.asset.dto.request;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
    private MultipartFile resourceFront;
    private MultipartFile resourceRear;
    private MultipartFile resourceSide;
    private MultipartFile resourceAdditional;
    private Boolean isEnabled;
    private Long brandId;
    private Long assetLineId;
    private Long assetSeasonId;
    //    private Long assetCollectionId;
    private Long assetBrandCategoryId;

    public AssetPrototype toEntityExcept(AssetPrototype entity){
        if(getName()!=null) entity.setName(this.getName());
        if(getDecorator()!=null) entity.setDecorator(this.getDecorator());
        if(getCode()!=null) entity.setCode(this.getCode());
        if(getDescription()!=null) entity.setDescription(this.getDescription());
        if(getReleasePrice()!=null) entity.setReleasePrice(this.getReleasePrice());
        if(getAdditional()!=null) entity.setAdditional(this.getAdditional());
        if(getKeywords()!=null) entity.setKeywords(this.getKeywords());
        if(getIsEnabled()!=null) entity.setIsEnabled(this.getIsEnabled());
        return entity;
    }
}
