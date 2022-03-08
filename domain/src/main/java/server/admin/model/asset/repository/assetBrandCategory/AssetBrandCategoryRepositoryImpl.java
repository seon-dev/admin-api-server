package server.admin.model.asset.repository.assetBrandCategory;

import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.asset.entity.QAsset;
import server.admin.model.asset.entity.QAssetCategory;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static server.admin.model.asset.entity.QAssetBrandCategory.*;

public class AssetBrandCategoryRepositoryImpl extends QueryDslSupport implements AssetBrandCategoryRepositoryCustom {

    @Autowired
    public AssetBrandCategoryRepositoryImpl(EntityManager entityManager){
        super(AssetBrandCategory.class, entityManager);
    }

    @Override
    public List<AssetBrandCategoryResponse.Minified> findMinifiedByBrandId(Long id) {
            return queryFactory.from(assetBrandCategory)
                    .where(assetBrandCategory.id.eq(id))
                    .select(Projections.constructor(AssetBrandCategoryResponse.Minified.class,
                            assetBrandCategory.id,
                            assetBrandCategory.brand.id,
                            assetBrandCategory.category.id,
                            assetBrandCategory.category.name,
                            assetBrandCategory.isEnabled
                            ))
                    .fetch();
    }

    @Override
    public Optional<AssetBrandCategory> findByIdFetchJoin(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(assetBrandCategory)
                .leftJoin(assetBrandCategory.brand, QBrand.brand).fetchJoin()
                .leftJoin(assetBrandCategory.category, QAssetCategory.assetCategory).fetchJoin()
                .where(
                        assetBrandCategory.id.eq(id)
                ).fetchOne()
        );
    }

    @Override
    public List<AssetBrandCategory> findAllFetchJoin() {
        return queryFactory.selectFrom(assetBrandCategory)
                .leftJoin(assetBrandCategory.brand, QBrand.brand).fetchJoin()
                .leftJoin(assetBrandCategory.category, QAssetCategory.assetCategory).fetchJoin()
                .fetch();
    }


}
