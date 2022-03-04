package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() { return uid + resourceExtension; }



}
