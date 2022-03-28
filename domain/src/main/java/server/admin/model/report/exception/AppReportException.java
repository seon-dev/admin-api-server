package server.admin.model.report.exception;

public class AppReportException {

    public static class AppReportNotExistException extends RuntimeException{
        public AppReportNotExistException(){
            super("해당 신고 내역이 존재하지 않습니다.");
        }
    }

}
