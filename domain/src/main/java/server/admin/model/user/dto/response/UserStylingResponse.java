package server.admin.model.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.styling.entity.UserStyling;

import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@Builder
public class UserStylingResponse {
    private Long id;
    private UserProfileResponse.Minified user;
    private String text;
    private int likes;
    private int comments;
    private List<String> resources;
    private List<AssetPrototypeResponse.Minified> referenceAssets;
    private Timestamp createdAt;
    private Boolean isArchived;
    private Boolean isEnabled;

    public UserStylingResponse toResponse(UserStyling entity){
        return UserStylingResponse.builder()
                .id(entity.getId())
                .user(UserProfileResponse.Minified.of(entity.getUser()))
                .text(entity.getText())
                .likes(entity.getLikes())
                .comments(entity.getComments())
                .build();
    }

}
