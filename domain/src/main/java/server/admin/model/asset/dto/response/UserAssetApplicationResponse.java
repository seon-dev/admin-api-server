package server.admin.model.asset.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.asset.entity.Asset;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.user.entity.User;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserAssetApplicationResponse {
    private Integer offer;
    private User user;
    private AssetPrototype assetPrototype;
    private Asset asset;
    private String resourceRear;
    private String resourceCertification;
    private String resourceReceipt;
    private Date purchaseDate;
    private Long verifierId;
    private Long verifiedAssetId;
    private String userComment;
    private String verifierComment;
//    private Boolean isVerified;

    public static UserAssetApplicationResponse toResponse(UserAssetApplication entity){
        return UserAssetApplicationResponse.builder()
                .offer(entity.getOffer())
                .user(entity.getUser())
                .assetPrototype(entity.getAssetPrototype())
                .asset(entity.getAsset())
                .resourceRear(entity.getResourceRear())
                .resourceCertification(entity.getResourceCertification())
                .resourceReceipt(entity.getResourceReceipt())
                .purchaseDate(entity.getPurchaseDate())
                .verifierId(entity.getVerifierId())
                .verifiedAssetId(entity.getVerifiedAssetId())
                .verifierComment(entity.getVerifierComment())
                .userComment(entity.getUserComment())
                .verifierComment(entity.getVerifierComment())
                .build();
    }
}
