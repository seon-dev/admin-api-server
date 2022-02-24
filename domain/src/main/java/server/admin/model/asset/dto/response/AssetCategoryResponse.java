package server.admin.model.asset.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.asset.entity.AssetCategory;

@Getter
@Builder
public class AssetCategoryResponse {
    private Long id;
    private String name;
    private String resource;
    private Boolean isEnabled;

    public static AssetCategoryResponse toResponse(AssetCategory entity){
        return AssetCategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .resource(entity.getResource())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
