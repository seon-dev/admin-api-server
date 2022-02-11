package server.admin.model.asset.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;

import javax.persistence.EntityManager;
import java.util.Optional;

import static server.admin.model.asset.entity.QAssetPrototype.*;

public class AssetPrototypeRepositoryImpl
        extends QueryDslSupport
        implements AssetPrototypeRepositoryCustom {

    @Autowired
    public AssetPrototypeRepositoryImpl(EntityManager entityManager) {
        super(UserAssetApplication.class, entityManager);
    }

    @Override
    public Optional<AssetPrototype> findByIdWithFetchJoin(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(assetPrototype)
                        .leftJoin(assetPrototype.brand, QBrand.brand).fetchJoin()
                        .leftJoin(assetPrototype.line, QAssetLine.assetLine).fetchJoin()
                        .leftJoin(assetPrototype.collection, QAssetCollection.assetCollection).fetchJoin()
                        .leftJoin(assetPrototype.season, QAssetSeason.assetSeason).fetchJoin()
                        .leftJoin(assetPrototype.brandCategory, QAssetBrandCategory.assetBrandCategory).fetchJoin()
                        .where(assetPrototype.id.eq(id))
                .fetchOne());
    }


}
