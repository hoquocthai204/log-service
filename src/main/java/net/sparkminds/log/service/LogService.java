package net.sparkminds.log.service;

import java.util.List;

import net.sparkminds.log.entity.Log;

public interface LogService {

    List<Log> getAllLog();

    void addNewLog(String message);

}
