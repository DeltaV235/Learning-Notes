package com.deltav.springcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {
    private String serviceName;
    private String servicePort;
    private String serviceDescription;
}
