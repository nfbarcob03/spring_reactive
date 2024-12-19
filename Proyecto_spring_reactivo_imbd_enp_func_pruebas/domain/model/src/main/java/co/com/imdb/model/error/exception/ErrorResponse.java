package co.com.imdb.model.error.exception;

import lombok.*;


import java.time.OffsetDateTime;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String             traceId;
    private OffsetDateTime timestamp;
    private String             message;
}
