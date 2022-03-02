package server.admin.model.badge.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.admin.model.badge.entity.Badge;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BadgeUpdateRequest {
    private String name;
    private String description;
    private String resourceUploaded;
    private String resourceExtension;
    private UUID uid = UUID.randomUUID();

    public String getResourceFileName() {
        return uid + resourceExtension;
    }

    public Badge setEntityExcept(Badge entity){
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }
}
