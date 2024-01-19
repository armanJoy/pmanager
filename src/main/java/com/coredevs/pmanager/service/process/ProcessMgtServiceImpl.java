package com.coredevs.pmanager.service.process;

import com.coredevs.pmanager.model.domain.process.ProcessInfo;
import com.coredevs.pmanager.model.domain.process.ProcessLog;
import com.coredevs.pmanager.model.view.ApiStatusVm;
import com.coredevs.pmanager.model.view.ProcessDefVm;
import com.coredevs.pmanager.model.view.process.ProcessInfoVm;
import com.coredevs.pmanager.repo.process.ProcessInfoRepo;
import com.coredevs.pmanager.repo.process.ProcessLogRepo;
import com.coredevs.pmanager.util.UtilService;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessMgtServiceImpl implements ProcessMgtService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProcessInfoRepo processInfoRepo;

    @Autowired
    private ProcessLogRepo processLogRepo;

    @PostConstruct
    private void initiateProcess() {
        List<ProcessInfo> processInfos = processInfoRepo.findAllByProEndTime(null);
        System.out.println("");
        if (processInfos != null && processInfos.size() > 0) {
            for (ProcessInfo processInfo : processInfos) {

                try {
                    scheduleProcess(processInfo);
                } catch (Exception e) {
                    logger.error("Error initiating process upon server restart for Id- " + processInfo.getProId() + "\n" + Arrays.deepToString(e.getStackTrace()));
                }
            }
        }
    }

    @Override
    public ApiStatusVm startProcess(ProcessDefVm processDefVm) {
        try {
            ProcessInfo processInfo = ProcessInfo.builder().proName(processDefVm.getProName()).proFreq(processDefVm.getProcessFreqMili()).build();
            processInfo = processInfoRepo.save(processInfo);
            if (processInfo != null && processInfo.getProId() != null) {
                scheduleProcess(processInfo);
            }
            ProcessInfoVm processInfoVm = ProcessInfoVm.builder().proId(processInfo.getProId()).proName(processInfo.getProName()).proFreq(processInfo.getProFreq()).createdAt(processInfo.getCreatedAt()).build();
            return new ApiStatusVm("Process created", true, processInfoVm);
        } catch (Exception e) {
            logger.error("Error occured during procees creation " + "\n" + Arrays.deepToString(e.getStackTrace()));
            return new ApiStatusVm("Error occured during process creation. Try later", false, null);
        }

    }

    void scheduleProcess(ProcessInfo processInfo) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    int randNum = UtilService.getRandomSixDigit();
                    String logStr = String.format("%s - Generated random number %d for process %s of ID- %d", LocalDateTime.now().toString().substring(0, 19), randNum, processInfo.getProName(), processInfo.getProId());
                    ProcessLog proLog = ProcessLog.builder().proId(processInfo.getProId()).logStr(logStr).build();
                    processLogRepo.save(proLog);
                    logger.info(logStr);
                } catch (Exception e) {
                    logger.error("Error occured during running procees of " + processInfo.getProId() + "\n" + Arrays.deepToString(e.getStackTrace()));
                }
            }

        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, processInfo.getProFreq(), processInfo.getProFreq());
        PROCESS_STORE.put(processInfo.getProId(), timer);
    }

    @Override
    public ApiStatusVm stopProcess(long proId) {
        Timer process = PROCESS_STORE.get(proId);
        if (process != null) {
            ProcessInfo processInfo = processInfoRepo.stopProcess(proId);
            process.cancel();
            PROCESS_STORE.remove(proId);
            ProcessInfoVm processInfoVm = ProcessInfoVm.builder().proId(proId).proName(processInfo.getProName()).proFreq(processInfo.getProFreq()).createdAt(processInfo.getCreatedAt()).proEndTime(processInfo.getProEndTime()).build();
            return new ApiStatusVm("Process stopped at " + LocalDateTime.now().toString(), true, processInfoVm);
        } else {
            return new ApiStatusVm("No running process found with id " + proId, false, null);
        }
    }

    @Override
    public List<String> getProcessLog(long proId) {
        List<ProcessLog> logs = processLogRepo.findAllByProIdOrderByCreatedAtDesc(proId);
        if (logs != null && logs.size() > 0) {
            return logs.stream().map(item -> item.getLogStr()).collect(Collectors.toList());
        } else {
            return processInfoRepo.findById(proId).isPresent() ? new ArrayList<>() : null;
        }

    }

}
