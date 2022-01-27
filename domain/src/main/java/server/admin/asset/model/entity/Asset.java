package server.admin.asset.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "asset")
@DynamicInsert
@DynamicUpdate
public class Asset extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_prototype_id")
    @JsonIgnore
    private AssetPrototype assetPrototype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_quality_id")
    @JsonIgnore
    private AssetQuality assetQuality;
}
