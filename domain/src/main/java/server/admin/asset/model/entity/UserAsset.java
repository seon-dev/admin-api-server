package server.admin.asset.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;
import server.admin.user.model.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_asset")
@DynamicUpdate
@DynamicInsert
public class UserAsset extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_prototype_id")
    @JsonIgnore
    private AssetPrototype assetPrototype;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_application_id")
    @JsonIgnore
    private UserAssetApplication userAssetApplication;
}
