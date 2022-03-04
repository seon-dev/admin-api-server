package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() {
        return uid + resourceExtension;
    }

    public static AssetCategory toEntityExceptResource(AssetCategoryCreateRequest request){
        AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName(request.name);
        assetCategory.setIsEnabled(request.isEnabled);
        return assetCategory;
    }
}
