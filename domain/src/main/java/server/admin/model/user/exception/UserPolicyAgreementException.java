package server.admin.model.user.exception;

public class UserPolicyAgreementException {

    public static class UserPolicyAgreementNotExistException extends RuntimeException{
        public UserPolicyAgreementNotExistException() {
            super("해당 유저는 개인 정보 약관동의가 필요합니다.");
        }
    }
}
