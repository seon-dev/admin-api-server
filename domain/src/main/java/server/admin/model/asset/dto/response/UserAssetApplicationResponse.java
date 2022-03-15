package server.admin.model.asset.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import server.admin.model.asset.entity.Asset;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.User;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAssetApplicationResponse {
    private Long id;
    private UserProfileResponse.Minified user;//userMinified로 변경하기->ok
    private AssetResponse asset;
//    private AssetPrototype assetPrototype;
    private String resourceFront;
    private String resourceRear;
    private String resourceCertification;
    private String resourceReceipt;
    private Timestamp purchaseDate;
    private Integer offer;
    private UserProfileResponse.Verifier verifier;
    private Long verifiedAssetId;
    private String userComment;
    private String verifierComment;
    private Boolean isVerified;
    private Timestamp verifiedAt;
    private Timestamp createAt;

    public static UserAssetApplicationResponse toResponseExceptVerifier(UserAssetApplication entity){
        return UserAssetApplicationResponse.builder()
                .id(entity.getId())
                .offer(entity.getOffer())
                .user(UserProfileResponse.Minified.of(entity.getUser()))
//                .assetPrototype(entity.getAssetPrototype())
                .asset(AssetResponse.toResponse(entity.getAsset()))
                .resourceFront(entity.getResourceFront())
                .resourceRear(entity.getResourceRear())
                .resourceCertification(entity.getResourceCertification())
                .resourceReceipt(entity.getResourceReceipt())
                .purchaseDate(entity.getPurchaseDate())
//                .verifier(entity.getVerifierId())
                .verifiedAssetId(entity.getVerifiedAssetId())
                .verifierComment(entity.getVerifierComment())
                .userComment(entity.getUserComment())
                .verifiedAt(entity.getVerifiedAt())
                .createAt(entity.getCreatedAt())
                .isVerified(entity.getIsVerified())
                .build();
    }
}
