package server.admin.model.styling.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.styling.entity.UserStylingComment;
import server.admin.model.user.dto.response.UserProfileResponse;

import java.sql.Timestamp;

@Getter
@Builder
public class UserStylingCommentResponse {
    private Long id;
    private UserProfileResponse.Minified user;
    private String text;
    private Timestamp createdAt;

    public static UserStylingCommentResponse toResponse(UserStylingComment entity){
        return UserStylingCommentResponse.builder()
                .id(entity.getId())
                .user(UserProfileResponse.Minified.of(entity.getUser()))
                .text(entity.getText())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
