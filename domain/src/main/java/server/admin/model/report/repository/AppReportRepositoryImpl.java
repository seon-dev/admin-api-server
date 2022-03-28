package server.admin.model.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.report.entity.AppReport;
import server.admin.model.report.entity.QAppReport;

import javax.persistence.EntityManager;
import java.util.List;

public class AppReportRepositoryImpl extends QueryDslSupport implements AppReportRepositoryCustom {

    @Autowired
    public AppReportRepositoryImpl(EntityManager entityManager){
        super(AppReport.class, entityManager);
    }


}
