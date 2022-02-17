//package server.admin.model.auth.dto.response;
//
//import lombok.Builder;
//import lombok.Getter;
//import org.springframework.http.HttpStatus;
//
//@Getter
//@Builder
//public class VerificationResponse {
//    private int code;
//    private String result;
//
//    public static VerificationResponse newInstance(HttpStatus status, String result){
//        return VerificationResponse.builder()
//                .code(status.value())
//                .result(result)
//                .build();
//    }
//}
