package com.coredevs.pmanager.service.process;

import com.coredevs.pmanager.model.view.ApiStatusVm;
import com.coredevs.pmanager.model.view.ProcessDefVm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import org.springframework.stereotype.Service;

@Service
public interface ProcessMgtService {

    static Map<Long, Timer> PROCESS_STORE = new HashMap<>();

    ApiStatusVm startProcess(ProcessDefVm processDefVm);

    ApiStatusVm stopProcess(long proId);

    List<String> getProcessLog(long proId);

}
