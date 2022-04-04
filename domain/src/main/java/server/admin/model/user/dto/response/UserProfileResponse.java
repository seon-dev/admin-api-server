package server.admin.model.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.user.entity.User;
import server.admin.model.user.entity.UserPolicyAgreement;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String name;
    private Date birthday;
    private User.UserRole role;
    private Integer follower;
    private Integer following;
    private String instagram;
    private String introduce;
    private String nickname;
    private String phoneNumber;
    private Integer numberOfAssets;
    private Integer numberOfStylings;
//    private String profileLink;
    private String resource;
    private List<BadgeResponse> badges;
    private UserPolicyAgreement policyAgreement;
    private Boolean isEnabled;

    public static UserProfileResponse toResponseExcept(User entity){
        return UserProfileResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthday(entity.getBirthday())
                .instagram(entity.getInstagram())
                .introduce(entity.getIntroduce())
                .nickname(entity.getNickname())
                .phoneNumber(entity.getPhoneNumber())
                .resource(entity.getResource())
                .isEnabled(entity.isEnabled())
                .following(entity.getFollowing())
                .follower(entity.getFollower())
                .role(entity.getRole())
                .numberOfAssets(entity.getNumberOfAssets())
                .numberOfStylings(entity.getNumberOfStylings())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Minified{
        private Long id;
        private String name;
        private String nickname;
        private String phoneNumber;
        private String resource;
        private Integer following;
        private Integer follower;
        private Integer numberOfAssets;
        private Integer numberOfStylings;


        public static Minified of(User entity) {
            if (entity==null) return null;
            return Minified.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .nickname(entity.getNickname())
                    .phoneNumber(entity.getPhoneNumber())
                    .resource(entity.getResource())
                    .following(entity.getFollowing())
                    .follower(entity.getFollower())
                    .numberOfAssets(entity.getNumberOfAssets())
                    .numberOfStylings(entity.getNumberOfStylings())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Verifier{
        private Long id;
        private String name;
        private String nickname;
        private String phoneNumber;
        private String resource;

        public static Verifier of(User entity){
            return Verifier.builder()
                    .id(entity.getId())
                    .name(entity.getUsername())
                    .nickname(entity.getNickname())
                    .phoneNumber(entity.getPhoneNumber())
                    .resource(entity.getResource())
                    .build();
        }
    }


}
