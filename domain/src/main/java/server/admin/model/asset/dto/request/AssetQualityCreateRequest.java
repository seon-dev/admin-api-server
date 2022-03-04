package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetQuality;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AssetQualityCreateRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Boolean isEnabled;

    public static AssetQuality toEntity(AssetQualityCreateRequest request){
        AssetQuality assetQuality = new AssetQuality();
        assetQuality.setName(request.name);
        assetQuality.setDescription(request.description);
        assetQuality.setIsEnabled(request.isEnabled);
        return assetQuality;
    }
}
