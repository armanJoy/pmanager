package com.coredevs.pmanager.model.view.process;

import com.coredevs.pmanager.model.domain.util.TimeAuditor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProcessLogVm {

    private long proLogId;

    private long proId;

    private String logStr;

}
