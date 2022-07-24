package net.sparkminds.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sparkminds.log.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
