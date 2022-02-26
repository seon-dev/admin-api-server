package server.admin.model.asset.dto.request;

import server.admin.model.asset.entity.AssetCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssetCategoryCreateRequest {
    @NotBlank
    private String name;
    private String resourceUploaded;
    private String resourceExtension;
    @NotNull
    private Boolean isEnabled;

    public AssetCategory toEntityExceptResource(){
        AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName(this.name);
        assetCategory.setIsEnabled(this.isEnabled);
        return assetCategory;
    }
}
