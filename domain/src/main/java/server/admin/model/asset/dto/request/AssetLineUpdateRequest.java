package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AssetLineUpdateRequest {
    private String name;
    private String resourceUploaded;
    private String resourceExtension;
    private UUID uid = UUID.randomUUID();

    public String getResourceFileName() {
        return uid + resourceExtension;
    }
}
