package com.coredevs.pmanager.model.domain.process;

import com.coredevs.pmanager.model.domain.util.TimeAuditor;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "ProcessLog")
public class ProcessLog extends TimeAuditor implements Serializable {

    @Id
    @Column(name = "proLogId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long proLogId;

    @Column(name = "proId", nullable = false)
    private long proId;

    @Column(name = "logStr", nullable = false)
    private String logStr;

}
