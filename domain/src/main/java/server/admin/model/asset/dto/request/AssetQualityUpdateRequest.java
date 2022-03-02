package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetQuality;

@Getter
@NoArgsConstructor
public class AssetQualityUpdateRequest {
    private String name;
    private String description;

    public AssetQuality setEntity(AssetQuality entity){
        entity.setName(this.name);
        entity.setDescription(this.description);
        return entity;
    }
}
