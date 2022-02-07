package server.admin.model.badge.exception;

public class BadgeException {

    public static class BadgeNotExistException extends RuntimeException{
        public BadgeNotExistException(){ super("해당 뱃지가 존재하지 않습니다."); }
    }
}
