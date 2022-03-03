package server.admin.model.asset.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.admin.model.asset.entity.AssetQuality;

@Getter
@Builder
public class AssetQualityResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean isEnabled;

    public static AssetQualityResponse toResponse(AssetQuality entity){
        return AssetQualityResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
