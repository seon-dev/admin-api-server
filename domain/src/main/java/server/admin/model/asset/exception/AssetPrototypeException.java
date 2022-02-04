package server.admin.model.asset.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AssetPrototypeException {

    public static class AssetPrototypeNotExistException extends RuntimeException{

        public AssetPrototypeNotExistException(){
            super("해당 에셋 프로토타입이 존재하지 않습니다.");
        }
    }
}
