package com.coredevs.pmanager.repo.process;

import com.coredevs.pmanager.model.domain.process.ProcessInfo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessInfoRepo extends JpaRepository<ProcessInfo, Long> {

    List<ProcessInfo> findAllByProEndTime(LocalDateTime proEndTime);

    @Query(value = "UPDATE process_info SET pro_end_time=CURRENT_TIMESTAMP WHERE pro_id=?1 RETURNING *", nativeQuery = true)
    ProcessInfo stopProcess(long proId);

}
