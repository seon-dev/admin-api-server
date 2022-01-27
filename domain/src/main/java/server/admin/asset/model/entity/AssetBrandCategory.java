package server.admin.asset.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.brand.model.entity.Brand;
import server.admin.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "asset_brand_category")
public class AssetBrandCategory extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private AssetCategory category;

    @Column
    private Boolean isEnabled;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brandCategory")
//    @JsonIgnore
//    private List<AssetLine> lines;
}
