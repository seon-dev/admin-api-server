package server.admin.model.asset.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetSeasonUpdateRequest {
    private String name;
    private String resourceUploaded;
    private String resourceExtension;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() { return uid + resourceExtension; }

}
