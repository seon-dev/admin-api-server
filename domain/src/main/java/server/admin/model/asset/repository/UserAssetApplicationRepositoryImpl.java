package server.admin.model.asset.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.asset.dto.response.AssetPrototypeResponse;
import server.admin.model.asset.dto.response.AssetResponse;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.asset.entity.QAsset;
import server.admin.model.asset.entity.QAssetPrototype;
import server.admin.model.asset.entity.UserAssetApplication;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.QUser;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static server.admin.model.asset.entity.QUserAssetApplication.*;

public class UserAssetApplicationRepositoryImpl
        extends QueryDslSupport
        implements UserAssetApplicationRepositoryCustom {

    @Autowired
    public UserAssetApplicationRepositoryImpl(EntityManager entityManager) {
        super(UserAssetApplication.class, entityManager);
    }

    @Override
    public Optional<UserAssetApplicationResponse> findResponseById(Long id) {
        return Optional.ofNullable(queryFactory.from(userAssetApplication)
                        .leftJoin(userAssetApplication.asset, QAsset.asset)
                        .leftJoin(userAssetApplication.user, QUser.user)
                        .leftJoin(userAssetApplication.assetPrototype, QAssetPrototype.assetPrototype)
                .where(userAssetApplication.id.eq(id))
                        .select(
                                projectUserAssetApplication()
                        )
                .fetchOne());
    }

    @Override
    public List<UserAssetApplicationResponse> getUserAssetApplications(Long cursorId, Integer size, Boolean isVerified){
            return queryFactory.from(userAssetApplication)
                    .where(
                            checkIsVerified(isVerified),
                            checkCursor(cursorId)
                    )
                    .leftJoin(userAssetApplication.assetPrototype, QAssetPrototype.assetPrototype)
                    .leftJoin(userAssetApplication.user, QUser.user)
                    .leftJoin(userAssetApplication.asset, QAsset.asset)
                    .leftJoin(userAssetApplication.assetPrototype.brand, QBrand.brand)
                    .limit(size)
                    .orderBy(userAssetApplication.id.desc())
                    .select(projectUserAssetApplication())
                    .fetch();
    }

    private BooleanExpression checkCursor(Long cursorId){
        return cursorId != null ? userAssetApplication.id.loe(cursorId) : null;
    }

    private BooleanExpression checkIsVerified(Boolean isVerified){
        return isVerified != null ? userAssetApplication.isVerified.eq(isVerified) : null;
    }

    private ConstructorExpression<AssetResponse> projectAssetResponse(){
        return Projections.constructor(AssetResponse.class,
                QAsset.asset.id, QAsset.asset.assetQuality.id,
                Projections.constructor(AssetPrototypeResponse.class,
                        userAssetApplication.assetPrototype.id,
                        userAssetApplication.assetPrototype.brand.id,
                        userAssetApplication.assetPrototype.line.id,
                        userAssetApplication.assetPrototype.season.id,
                        userAssetApplication.assetPrototype.brandCategory.id,
                        userAssetApplication.assetPrototype.name,
                        userAssetApplication.assetPrototype.decorator,
                        userAssetApplication.assetPrototype.code,
                        userAssetApplication.assetPrototype.description,
                        userAssetApplication.assetPrototype.releasePrice,
                        userAssetApplication.assetPrototype.additional,
                        userAssetApplication.assetPrototype.trendy,
                        userAssetApplication.assetPrototype.keywords,
                        userAssetApplication.assetPrototype.resourceFront,
                        userAssetApplication.assetPrototype.resourceAdditional,
                        userAssetApplication.assetPrototype.resourceRear,
                        userAssetApplication.assetPrototype.resourceSide,
                        userAssetApplication.assetPrototype.isEnabled
                )
        );
    }

    private ConstructorExpression<UserAssetApplicationResponse> projectUserAssetApplication(){
        return Projections.constructor(UserAssetApplicationResponse.class,
                userAssetApplication.id,
                Projections.constructor(UserProfileResponse.Minified.class,
                        userAssetApplication.user.id,
                        userAssetApplication.user.nickname,
                        userAssetApplication.user.resource
                ),
                projectAssetResponse(),
                userAssetApplication.resourceFront,
                userAssetApplication.resourceRear,
                userAssetApplication.resourceCertification,
                userAssetApplication.resourceReceipt,
                userAssetApplication.purchaseDate,
                userAssetApplication.offer,
                userAssetApplication.verifierId,
                userAssetApplication.verifiedAssetId,
                userAssetApplication.userComment,
                userAssetApplication.verifierComment,
                userAssetApplication.isVerified,
                userAssetApplication.verifiedAt,
                userAssetApplication.createdAt
        );
    }

}
