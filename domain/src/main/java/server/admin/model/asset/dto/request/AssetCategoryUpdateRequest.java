package server.admin.model.asset.dto.request;

import server.admin.model.asset.entity.AssetCategory;

import javax.validation.constraints.NotBlank;

public class AssetCategoryUpdateRequest {
    @NotBlank
    private String name;
    private String resourceUploaded;
    private String resourceExtension;


}

