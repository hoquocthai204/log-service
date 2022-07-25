package net.sparkminds.log.service.impl;

import java.util.List;

import org.hibernate.validator.constraints.Length;
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
    @KafkaListener(topicPattern = "${kafka.topic_name}", groupId = "${kafka.group_id}")
    public void addNewLog(String message) {
        Log log = new Log();
        log.setEmail(message.split(", ")[0].substring(1));
        log.setIp(message.split(", ")[1]);
        log.setMessage(message.split(", ")[2].toString().replaceAll("\"", ""));
        logRepository.save(log);
    }
}
