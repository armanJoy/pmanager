package com.coredevs.pmanager.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiStatusVm {

    private String msg = "";

    private boolean jobDone = false;

    private Object data;

}
