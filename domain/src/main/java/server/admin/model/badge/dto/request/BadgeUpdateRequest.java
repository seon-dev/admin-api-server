package server.admin.model.badge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() {
        return uid + resourceExtension;
    }

    public static Badge setEntityExcept(Badge entity, BadgeUpdateRequest request){
        entity.setName(request.name);
        entity.setDescription(request.description);
        return entity;
    }
}
