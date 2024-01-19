package com.coredevs.pmanager.model.domain.process;

import com.coredevs.pmanager.model.domain.util.TimeAuditor;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "ProcessInfo")
public class ProcessInfo extends TimeAuditor implements Serializable {

    @Id
    @Column(name = "proId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proId;

    @Column(name = "proName", nullable = false, length = 100)
    private String proName;

    @Column(name = "proFreq", nullable = false)
    private long proFreq;

    @Column(name = "proEndTime")
    private LocalDateTime proEndTime;

}
