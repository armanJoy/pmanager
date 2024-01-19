package com.coredevs.pmanager.model.view.process;

import java.time.LocalDateTime;
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
public class ProcessInfoVm {

    private Long proId;

    private String proName;

    private long proFreq;

    private LocalDateTime createdAt;

    private LocalDateTime proEndTime;

}
