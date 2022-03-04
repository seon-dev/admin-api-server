package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetQuality;

@Getter
@NoArgsConstructor
public class AssetQualityUpdateRequest {
    private String name;
    private String description;

    public static AssetQuality setEntity(AssetQuality entity, AssetQualityUpdateRequest request){
        entity.setName(request.name);
        entity.setDescription(request.description);
        return entity;
    }
}
