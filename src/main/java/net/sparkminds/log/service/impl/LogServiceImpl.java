package net.sparkminds.log.service.impl;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.log.entity.Log;
import net.sparkminds.log.repository.LogRepository;
import net.sparkminds.log.service.LogService;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    @Override
    @KafkaListener(topicPattern = "my-topic", groupId = "myGroup")
    public void addNewLog(String message) {
        Log log = new Log();
        String[] messArr = message.split(",");
        log.setEmail(messArr[0]);
        log.setIp(messArr[1]);
        log.setMessage(messArr[2]);
        logRepository.save(log);
    }
}
