package com.kodilla.rentalcars.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
    
    public Log saveLog(final Log log){
        return logRepository.save(log);
    }
}
