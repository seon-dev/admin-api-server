package server.admin.model.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@Builder
public class VerificationResponse {
    private String message;
    private Timestamp expiredAt;

    public static VerificationResponse newInstance(String message, Timestamp expiredAt){
        return VerificationResponse.builder()
                .message(message)
                .expiredAt(expiredAt)
                .build();
    }
}
