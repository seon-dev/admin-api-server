package server.admin.model.badge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.admin.model.badge.entity.Badge;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BadgeCreateRequest {
    @NotNull
    private String name;
    private String description;
    @NotBlank
    private String resourceUploaded;
    @NotBlank
    private String resourceExtension;
    @NotNull
    private Boolean isEnabled;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName() {
        return uid + resourceExtension;
    }

    public Badge toEntityExcept(){
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        badge.setIsEnabled(isEnabled);
        return badge;
    }
}
