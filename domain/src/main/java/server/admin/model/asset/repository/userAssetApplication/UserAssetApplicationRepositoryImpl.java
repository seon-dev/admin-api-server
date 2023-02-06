package server.admin.model.asset.repository.userAssetApplication;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import server.admin.model.asset.dto.response.*;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.entity.QUser;

import javax.persistence.EntityManager;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
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
    public Optional<UserAssetApplication> findUserAssetApplicationById(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(userAssetApplication)
                        .leftJoin(userAssetApplication.asset, QAsset.asset).fetchJoin()
                        .leftJoin(userAssetApplication.user, QUser.user).fetchJoin()
                        .leftJoin(userAssetApplication.assetPrototype, QAssetPrototype.assetPrototype).fetchJoin()
                .where(userAssetApplication.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<UserAssetApplication> getUserAssetApplications(
//            Long cursorId, Integer size, Boolean isVerified, Sort sort
            Boolean isEnabled
    ){
        List<UserAssetApplication> userAssetApplicationList = queryFactory.selectFrom(userAssetApplication)
                .where(
//                        checkIsVerified(isVerified),
//                        checkCursor(cursorId)
                        checkIsEnabled(isEnabled)
                )
//                .leftJoin(userAssetApplication.assetPrototype, QAssetPrototype.assetPrototype)
                .leftJoin(userAssetApplication.assetPrototype,QAssetPrototype.assetPrototype).fetchJoin()
                .leftJoin(userAssetApplication.user, QUser.user).fetchJoin()
                .leftJoin(userAssetApplication.asset, QAsset.asset).fetchJoin()
//                .leftJoin(userAssetApplication.assetPrototype.brand, QBrand.brand).fetchJoin()
//                .leftJoin(userAssetApplication.assetPrototype.season, QAssetSeason.assetSeason).fetchJoin()
//                .leftJoin(userAssetApplication.assetPrototype.brandCategory, QAssetBrandCategory.assetBrandCategory).fetchJoin()
//                .leftJoin(userAssetApplication.assetPrototype.line, QAssetLine.assetLine).fetchJoin()
                .fetch()
                ;

//                .leftJoin(QAssetLine.assetLine).on(QAssetLine.assetLine.brand.eq(QBrand.brand));
//                .limit(size);

//        List<UserAssetApplicationResponse> userAssetApplicationResponses = Objects.requireNonNull(getQuerydsl())
////                .applySorting(sort, query)
//                .applyPagination(Pageable.unpaged(), query)
//                .select(projectUserAssetApplication())
//                .fetch();


        return userAssetApplicationList;

    }

    private BooleanExpression checkCursor(Long cursorId){
        return cursorId != null ? userAssetApplication.id.loe(cursorId) : null;
    }

    private BooleanExpression checkIsVerified(Boolean isVerified){
        return isVerified != null ? userAssetApplication.isVerified.eq(isVerified) : null;
    }

    private BooleanExpression checkIsEnabled(Boolean isEnabled){
        return isEnabled != null ? userAssetApplication.isEnabled.eq(isEnabled) : null;
    }

//    private OrderSpecifier<Long> OrderByAsc(String orderBy){
//        //정렬 하는 조건을 동적으로 가능하게 하기 ex. 조인되어있는 벨류에 대한 정렬이라서.. 안되는거였음
//        if(Objects.equals(orderBy, "id")) return userAssetApplication.id.asc();
//        if(Objects.equals(orderBy, "asset")) return userAssetApplication.asset.id.asc();
//        ...
//
//
////        return orderBy == "" ?  userAssetApplication.id.desc() : ;
//    }

    private ConstructorExpression<AssetResponse> projectAssetResponse(){
        return Projections.constructor(AssetResponse.class,
                QAsset.asset.id,
                QAsset.asset.assetQuality.id,
                Projections.constructor(AssetPrototypeResponse.class,
                        userAssetApplication.assetPrototype.id,
                        projectBrand(),
                        projectAssetLine(),
                        projectAssetSeason(),
                        projectAssetBrandCategory(),
                        userAssetApplication.assetPrototype.name,
                        userAssetApplication.assetPrototype.decorator,
                        userAssetApplication.assetPrototype.code,
                        userAssetApplication.assetPrototype.description,
                        userAssetApplication.assetPrototype.releasePrice,
                        userAssetApplication.assetPrototype.additional,
                        userAssetApplication.assetPrototype.trendy,
                        userAssetApplication.assetPrototype.likes,
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

    private ConstructorExpression<BrandResponse.Minified> projectBrand(){
        return Projections.constructor(BrandResponse.Minified.class,
                QBrand.brand.id,
                QBrand.brand.name,
                QBrand.brand.originalName,
                QBrand.brand.resource
        );
    }

    private ConstructorExpression<AssetLineResponse.Minified> projectAssetLine(){
        return Projections.constructor(AssetLineResponse.Minified.class,
                QAssetLine.assetLine.id,
                QAssetLine.assetLine.brand.id,
                QAssetLine.assetLine.brandCategory.id,
                QAssetLine.assetLine.name,
                QAssetLine.assetLine.resource,
                QAssetLine.assetLine.isEnabled
                );
    }

    private ConstructorExpression<AssetSeasonResponse> projectAssetSeason(){
        return Projections.constructor(AssetSeasonResponse.class,
                userAssetApplication.assetPrototype.season.id,
                userAssetApplication.assetPrototype.season.name,
                userAssetApplication.assetPrototype.season.resource,
                userAssetApplication.assetPrototype.season.isEnabled
                );
    }

    private ConstructorExpression<AssetBrandCategoryResponse.Minified> projectAssetBrandCategory(){
        return Projections.constructor(AssetBrandCategoryResponse.Minified.class,
                QAssetBrandCategory.assetBrandCategory.id,
                QAssetBrandCategory.assetBrandCategory.brand.id,
                QAssetBrandCategory.assetBrandCategory.category.id,
                QAssetBrandCategory.assetBrandCategory.category.name,
                QAssetBrandCategory.assetBrandCategory.isEnabled
                );
    }

}
