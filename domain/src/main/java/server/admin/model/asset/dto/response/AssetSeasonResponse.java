package server.admin.model.asset.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import server.admin.model.asset.entity.AssetSeason;
@Getter
@Builder
@AllArgsConstructor
public class AssetSeasonResponse {
    private Long id;
    private String name;
    private String resource;
    private Boolean isEnabled;

    public static AssetSeasonResponse toResponse(AssetSeason entity){
        return AssetSeasonResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .resource(entity.getResource())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
