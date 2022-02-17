package server.admin.model.auth.exception;

public class AuthException {
    public static class InvalidEmailVerificationCodeException extends RuntimeException{
        public InvalidEmailVerificationCodeException(){
            super("유효한 인증번호가 아닙니다.");
        }
    }

    public static class InvalidEmailAddressException extends RuntimeException{
        public InvalidEmailAddressException(){
            super("인증되지 않은 이메일 입니다. 이메일 인증을 먼저 해주세요.");
        }
    }

    public static class InvalidNameException extends RuntimeException{
        public InvalidNameException(){
            super("관리자 이름은 한글만 가능합니다.");
        }
    }

    public static class InvalidPasswordException extends RuntimeException{
        public InvalidPasswordException(){
            super("영문자, 숫자, 특수문자 포함 8자 이상만 가능합니다.");
        }
    }

    public static class SignInFailException extends RuntimeException{
        public SignInFailException(){
            super("로그인에 실패했습니다.");
        }
    }
}
