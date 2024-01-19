package com.coredevs.pmanager.repo.process;

import com.coredevs.pmanager.model.domain.process.ProcessLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessLogRepo extends JpaRepository<ProcessLog, Long> {

    List<ProcessLog> findAllByProIdOrderByCreatedAtDesc(long proId);

}
