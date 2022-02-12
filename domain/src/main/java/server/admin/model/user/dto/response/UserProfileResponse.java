package server.admin.model.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.user.entity.User;

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
    private List<BadgeResponse> badges;// List<BadgeResponse.Minified>

    public static UserProfileResponse toResponseWithoutBadge(User entity){
        return UserProfileResponse.builder()
                .id(entity.getId())
                .username(entity.getName())
                .birthday(entity.getBirthday())
                .instagram(entity.getInstagram())
                .introduce(entity.getIntroduce())
                .nickname(entity.getNickname())
                .phoneNumber(entity.getPhoneNumber())
                .resource(entity.getResource())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
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
