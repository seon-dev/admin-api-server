package server.admin.model.asset.dto.request;

import lombok.Getter;
import lombok.Setter;
import server.admin.model.asset.entity.Asset;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.user.entity.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class UserAssetApplicationUpdateRequest {
//    @NotNull
//    private Integer offer;
//    @NotNull
//    private Long userId;
//    @NotNull
//    private Long assetId;
//    private String resourceFront;
//    private String resourceRear;
//    private String resourceCertification;
//    private String resourceReceipt;
//    private Timestamp purchaseDate;

    private Long verifierId;
    @NotNull
    private Long verifiedAssetId;
//    private String userComment;
    private String verifierComment;
    @NotNull
    private Boolean isVerified;
    @NotNull
    private Timestamp verifiedAt;

//    @NotNull
//    private Long assetPrototypeId;

}
