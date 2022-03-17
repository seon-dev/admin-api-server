package server.admin.model.user.exception;

public class AdminException {
    public static class AdminNotExistException extends RuntimeException{
        public AdminNotExistException(){
            super("해당 어드민이 존재하지 않습니다.");
        }
    }

    public static class DuplicateAdminException extends RuntimeException{
        public DuplicateAdminException(){
            super("해당 어드민이 이미 존재합니다.");
        }
    }
}
