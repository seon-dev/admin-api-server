package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() { return uid + resourceExtension; }



}
