package server.admin.model.asset.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetCategoryCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String resourceUploaded;
    private String resourceExtension;
    @NotNull
    private Boolean isEnabled;
    private UUID uid = UUID.randomUUID();

    public String getResourceFileName() {
        return uid + resourceExtension;
    }

    public AssetCategory toEntityExceptResource(){
        AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName(this.name);
        assetCategory.setIsEnabled(this.isEnabled);
        return assetCategory;
    }
}
