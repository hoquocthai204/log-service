package net.sparkminds.log.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.log.entity.Log;
import net.sparkminds.log.service.LogService;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {
    
    private final LogService logService;
    
    @GetMapping
    public ResponseEntity<List<Log>> getAllLog(){
        return ResponseEntity.ok(logService.getAllLog());
    }
}
