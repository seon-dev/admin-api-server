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

    public AssetQuality toEntity(){
        AssetQuality assetQuality = new AssetQuality();
        assetQuality.setName(this.name);
        assetQuality.setDescription(this.description);
        assetQuality.setIsEnabled(this.isEnabled);
        return assetQuality;
    }
}
