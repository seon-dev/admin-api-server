package server.admin.model.asset.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.brand.entity.Brand;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "asset_brand_category")
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brandCategory")
//    @JsonIgnore
//    private List<AssetLine> lines;
}
