package server.admin.model.brand.exception;

public class BrandException {

    public static class BrandNotExistException extends RuntimeException{
        public BrandNotExistException(){
            super("해당 브랜드가 존재하지 않습니다.");
        }
    }
}
