package server.admin.styling.model.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.asset.model.entity.AssetPrototype;
import server.admin.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "user_styling_asset_reference")
@DynamicInsert
@DynamicUpdate
public class UserStylingAssetReference extends BaseTimeEntity implements Serializable {
    @Id @Generated
    private Long id;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="styling_id")
    private UserStyling userStyling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_prototype_id")
    private AssetPrototype assetPrototype;

}
