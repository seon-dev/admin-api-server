package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.admin.model.asset.entity.AssetLine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetLineCreateRequest {
    @NotNull
    private Long brandId;
    @NotNull
    private Long brandCategoryId;
    @NotBlank
    private String name;
    @NotBlank
    private String resourceUploaded;
    @NotBlank
    private String resourceExtension;
    @NotNull
    private Boolean isEnabled;
    private UUID uid = UUID.randomUUID();

    public String getResourceFileName() { return uid + resourceExtension; }

//    public AssetLine toEntityExcept(){
//        AssetLine assetLine = new AssetLine();
//        assetLine.setName(name);
//        assetLine.setIsEnabled(isEnabled);
//    }

}
