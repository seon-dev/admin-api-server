package server.admin.model.report.exception;

public class AppReportCategoryException {

    public static class AppReportCategoryNotExistException extends RuntimeException{
        public AppReportCategoryNotExistException(){
            super("해당 신고 카테고리 항목이 존재하지 않습니다.");
        }
    }
}
