package server.admin.model.styling.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.asset.entity.QAssetPrototype;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.styling.entity.QUserStyling;
import server.admin.model.styling.entity.QUserStylingAssetReference;
import server.admin.model.styling.entity.UserStylingAssetReference;

import javax.persistence.EntityManager;
import java.util.List;

public class UserStylingAssetReferenceRepositoryImpl extends QueryDslSupport  implements UserStylingAssetReferenceRepositoryCustom{
    @Autowired
    public UserStylingAssetReferenceRepositoryImpl(EntityManager entityManager){
        super(UserStylingAssetReference.class, entityManager);
    }


    @Override
    public List<AssetPrototypeResponse.Minified> findMinifiedByStylingId(Long id) {
        JPAQuery<?> query = queryFactory.from(QUserStylingAssetReference.userStylingAssetReference)
                .leftJoin(QUserStylingAssetReference.userStylingAssetReference.userStyling, QUserStyling.userStyling)
                .leftJoin(QUserStylingAssetReference.userStylingAssetReference.assetPrototype,QAssetPrototype.assetPrototype)
                .where(QUserStylingAssetReference.userStylingAssetReference.userStyling.id.eq(id));

        return query.select(Projections.constructor(AssetPrototypeResponse.Minified.class,
                        QAssetPrototype.assetPrototype.id,
                        QAssetPrototype.assetPrototype.name,
                        QAssetPrototype.assetPrototype.code,
                        Projections.constructor(BrandResponse.Minified.class,
                                QAssetPrototype.assetPrototype.brand.id,
                                QAssetPrototype.assetPrototype.brand.name,
                                QAssetPrototype.assetPrototype.brand.originalName,
                                QAssetPrototype.assetPrototype.brand.resource
                        ),
                        QAssetPrototype.assetPrototype.releasePrice,
                        QAssetPrototype.assetPrototype.trendy,
                        QAssetPrototype.assetPrototype.likes,
                        QAssetPrototype.assetPrototype.isEnabled,
                        QAssetPrototype.assetPrototype.resourceFront
                ))
                .fetch();
    }

}
