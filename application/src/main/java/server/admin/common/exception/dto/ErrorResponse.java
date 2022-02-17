package server.admin.common.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final Timestamp timestamp;
    private final int statusCode;//reason으로 고치기
    private final String description;
    private final HttpStatus status;

    public ErrorResponse(int statusCode, String description, HttpStatus status) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.statusCode = status.value();
        this.description = description;
        this.status = status;
    }
}
