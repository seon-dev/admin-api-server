package server.admin.common.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final String statusCode;
    private final String description;
    private final HttpStatus status;

    public ErrorResponse(LocalDateTime timestamp, String statusCode, String description, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.description = description;
        this.status = status;
    }
}
