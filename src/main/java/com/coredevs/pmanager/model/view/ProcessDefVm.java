package com.coredevs.pmanager.model.view;

import com.coredevs.pmanager.model.domain.util.TimeAuditor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProcessDefVm {

    @NotEmpty(message = "Process name is required")
    private String proName;

    @Min(value = 60000, message = "process frequencry should be 1 minute or more")
    private long processFreqMili;

}
