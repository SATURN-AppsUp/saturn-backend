package com.example.saturn.models.log;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.logging.Level;

@AllArgsConstructor
@Data
public class LogMessage {
    private final long timestamp;
    private final Level level;
    private final String logger;
    private final String message;

}
