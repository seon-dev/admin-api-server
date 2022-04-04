package server.admin.model.user.exception;

public class UserException {

    public static class UserNotExistException extends RuntimeException {
        public UserNotExistException() {
            super("해당 유저가 존재하지 않습니다.");
        }
    }

    public static class UserDuplicatedException extends RuntimeException{
        public UserDuplicatedException(){
            super("해당 유저가 이미 존재합니다.");
        }
    }

    public static class UserWithdrawedException extends RuntimeException{
        public UserWithdrawedException() { super("해당 유저는 탈퇴한 유저입니다."); }
    }
}
