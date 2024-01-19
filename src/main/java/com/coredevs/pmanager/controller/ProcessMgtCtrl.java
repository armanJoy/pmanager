package com.coredevs.pmanager.controller;

import com.coredevs.pmanager.model.view.ApiStatusVm;
import com.coredevs.pmanager.model.view.ProcessDefVm;
import com.coredevs.pmanager.service.process.ProcessMgtService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/process")
public class ProcessMgtCtrl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProcessMgtService processMgtService;

    @PostMapping(value = "/start")
    public ResponseEntity<ApiStatusVm> startProcess(@Valid @RequestBody ProcessDefVm processDef) {
        logger.info(this.getClass().getName() + " :inside startProcess method");
        ApiStatusVm res = processMgtService.startProcess(processDef);
        return new ResponseEntity(res, res.isJobDone() ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PatchMapping(value = "/stop")
    public ResponseEntity<ApiStatusVm> stopProcess(@Positive(message = "invalid process id") @RequestParam long processId) {
        logger.info(this.getClass().getName() + " :inside stopProcess method");
        ApiStatusVm res = processMgtService.stopProcess(processId);
        return new ResponseEntity(res, res.isJobDone() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/logs")
    public ResponseEntity<List<String>> getProcessLogs(@Positive(message = "invalid process id") @RequestParam long processId) {
        logger.info(this.getClass().getName() + " :inside getProcessLogs method");
        List<String> res = processMgtService.getProcessLog(processId);
        return new ResponseEntity(res != null ? res : "No process found with this id", res != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
