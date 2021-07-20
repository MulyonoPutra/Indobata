package com.labs.indobata.utils;

import com.labs.indobata.domain.dto.ResponseMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<ResponseMessages> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new ResponseMessages(
                        httpStatus.value(),
                        httpStatus,
                        httpStatus.getReasonPhrase().toUpperCase(),
                        message
                ),
                httpStatus
        );
    }
}
