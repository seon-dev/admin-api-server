package server.admin.model.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.admin.model.badge.entity.Badge;
import server.admin.model.user.entity.User;
import server.admin.model.user.entity.UserBadge;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String username;
    private Date birthday;
//    private Integer follower;
//    private Integer following;
    private String instagram;
    private String introduce;
    private String nickname;
    private String phoneNumber;
//    private String profileLink;
    private String resource;
    private List<Badge> badges;// List<BadgeResponse.Minified>

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Minified{
        private Long id;
        private String nickname;
        private String resource;

        public static Minified of(User entity) {
            return Minified.builder()
                    .id(entity.getId())
                    .nickname(entity.getNickname())
                    .resource(entity.getResource())
                    .build();
        }
    }


}
