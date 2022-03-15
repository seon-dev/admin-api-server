package server.admin.model.event.exception;

public class AppEventException {
    public static class AppEventNotExistException extends RuntimeException{
        public AppEventNotExistException(){
            super("해당 앱 이벤트가 존재하지 않습니다.");
        }
    }
}
