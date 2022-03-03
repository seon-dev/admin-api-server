package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetSeasonCreateRequest {
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



}
