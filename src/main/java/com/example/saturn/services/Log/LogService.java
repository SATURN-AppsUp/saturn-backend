package com.example.saturn.services.Log;

import com.example.saturn.models.log.LogMessage;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class LogService {
        private static final Logger log = Logger.getLogger(LogService.class.getName());
        MongoTemplate template;
        public void logToDatabase(LogMessage logMessage) {
                template.save(logMessage);
        }

}
