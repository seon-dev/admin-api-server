package server.admin.model.asset.dto.response;

import lombok.*;
import server.admin.model.asset.entity.Asset;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {
    private Long id;
    private Long assetQualityId;
    private AssetPrototypeResponse assetPrototype;

    public static AssetResponse toResponse(Asset entity){
        return AssetResponse.builder()
                .id(entity.getId())
                .assetQualityId(entity.getAssetQuality().getId())
                .assetPrototype(AssetPrototypeResponse.toResponse(entity.getAssetPrototype()))
                .build();
    }
}
