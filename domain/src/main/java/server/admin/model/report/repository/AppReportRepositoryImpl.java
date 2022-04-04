package server.admin.model.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.report.entity.AppReport;
import server.admin.model.report.entity.QAppReport;
import server.admin.model.user.entity.QUser;

import javax.persistence.EntityManager;
import java.util.List;

public class AppReportRepositoryImpl
        extends QueryDslSupport
        implements AppReportRepositoryCustom {

    @Autowired
    public AppReportRepositoryImpl(EntityManager entityManager){
        super(AppReport.class, entityManager);
    }


    @Override
    public List<AppReport> findAllFetchJoin() {
        return queryFactory.selectFrom(QAppReport.appReport)
                .leftJoin(QAppReport.appReport)
                .on(QUser.user.id.eq(QAppReport.appReport.reporteeId))
                .fetchJoin()
                .leftJoin(QAppReport.appReport)
                .on(QUser.user.id.eq(QAppReport.appReport.reporterId))
                .fetchJoin()
                .leftJoin(QAppReport.appReport)
                .on(QUser.user.id.eq(QAppReport.appReport.verifierId))
                .fetchJoin()
                .fetch();
    }
}
