package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssetBrandCategoryCreateRequest {
    private Long brandId;
    private Long assetCategoryId;
    private Boolean isEnabled;
}
