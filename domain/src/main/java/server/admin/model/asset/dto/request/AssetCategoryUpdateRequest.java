package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetCategory;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetCategoryUpdateRequest {
    private String name;
    private String resourceUploaded;
    private String resourceExtension;
    private UUID uid = UUID.randomUUID();

    public String getResourceFileName() {
        return uid + resourceExtension;
    }
}

