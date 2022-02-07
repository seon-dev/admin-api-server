package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.Setter;
import server.admin.model.asset.entity.Asset;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.user.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserAssetApplicationCreateRequest {
    @NotNull
    private Integer offer;
    @NotNull
    private User user;
    @NotNull
    private AssetPrototype assetPrototype;
    @NotNull
    private Asset asset;
    private String resourceRear;
    private String resourceCertification;
    private String resourceReceipt;
    private Date purchaseDate;
    private Long verifierId;
    private Long verifiedAssetId;
    private String userComment;
    private String verifierComment;
    private Boolean isVerified = false;


}
