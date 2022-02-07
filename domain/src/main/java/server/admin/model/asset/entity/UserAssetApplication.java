package server.admin.model.asset.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_asset_application")
@DynamicInsert
@DynamicUpdate
public class UserAssetApplication extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_prototype_id")
    @JsonIgnore
    private AssetPrototype assetPrototype;

    // 희망하는 에셋 형태 (퀼리티 등)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = true)
    @JsonIgnore
    private Asset asset;

    @Column(name = "asset_resource_front", length = 100)
    private String resourceFront;

    @Column(name = "asset_resource_rear", length = 100)
    private String resourceRear;

    @Column(name = "asset_resource_certification", nullable = true, length = 100)
    private String resourceCertification;

    @Column(name = "asset_resource_receipt", nullable = true, length = 100)
    private String resourceReceipt;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    // 희망 판매가
    @Column(name = "offer")
    private Integer offer;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verifier_id", nullable = true)
    private Long verifierId;

    @Column(name = "verified_asset_id", nullable = true)
    private Long verifiedAssetId;

    @Column(name = "user_comment", length = 100)
    private String userComment;

    @Column(name = "verifier_comment", length = 100)
    private String verifierComment;

    @CreationTimestamp
    @Column(name = "verified_at")
    private Timestamp verifiedAt;

    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

}
