package server.admin.model.asset.repository.assetLine;

import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.dto.response.AssetLineResponse;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.asset.entity.QAssetBrandCategory;
import server.admin.model.asset.entity.QAssetLine;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static server.admin.model.asset.entity.QAssetLine.*;

public class AssetLineRepositoryImpl extends QueryDslSupport implements AssetLineRepositoryCustom {

    @Autowired
    public AssetLineRepositoryImpl(EntityManager entityManager){
        super(AssetLine.class, entityManager);
    }


    @Override
    public Optional<AssetLine> findByIdFetchJoin(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(assetLine)
                .leftJoin(assetLine.brandCategory, QAssetBrandCategory.assetBrandCategory).fetchJoin()
                .leftJoin(assetLine.brand, QBrand.brand).fetchJoin()
                .where(assetLine.id.eq(id))
                .fetchOne());

    }

    @Override
    public List<AssetLine> findAllFetchJoin() {
        return queryFactory.selectFrom(assetLine)
                .leftJoin(assetLine.brandCategory, QAssetBrandCategory.assetBrandCategory).fetchJoin()
                .leftJoin(assetLine.brand, QBrand.brand).fetchJoin()
                .fetch();
    }

    @Override
    public List<AssetLineResponse> findResponseByBrandCategoryId(Long id){
        return queryFactory.from(assetLine)
                .leftJoin(assetLine.brandCategory, QAssetBrandCategory.assetBrandCategory)
                .leftJoin(assetLine.brand, QBrand.brand)

                .where(
                        assetLine.brandCategory.id.eq(id)
                )
                .select(Projections.constructor(AssetLineResponse.class,
                        assetLine.id,
                        Projections.constructor(BrandResponse.Minified.class,
                                assetLine.brand.id,
                                assetLine.brand.name,
                                assetLine.brand.originalName,
                                assetLine.brand.resource
                                ),
                        Projections.constructor(AssetBrandCategoryResponse.Minified.class,
                                assetLine.brandCategory.id,
                                assetLine.brandCategory.brand.id,
                                assetLine.brandCategory.category.id,
                                assetLine.brandCategory.category.name,
                                assetLine.isEnabled
                                ),
                        assetLine.name,
                        assetLine.resource,
                        assetLine.isEnabled
                        )
                )
                .fetch();
    }


}
