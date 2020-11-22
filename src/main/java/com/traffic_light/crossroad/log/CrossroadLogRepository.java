package com.traffic_light.crossroad.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossroadLogRepository extends JpaRepository<CrossroadLog, Long>, JpaSpecificationExecutor<CrossroadLog> {
}
