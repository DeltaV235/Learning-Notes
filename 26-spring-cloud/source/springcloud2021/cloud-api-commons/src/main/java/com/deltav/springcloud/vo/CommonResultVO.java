package com.deltav.springcloud.vo;

import com.deltav.springcloud.constant.PaymentConstant;
import com.deltav.springcloud.dto.ServiceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResultVO<T> {
    private String code;
    private String message;
    private T data;
    private ServiceInfo serviceInfo;

    public static <E> CommonResultVO<E> successWithoutData(ServiceInfo serviceInfo) {
        return new CommonResultVO<>(PaymentConstant.CODE_SUCCESS, PaymentConstant.MESSAGE_SUCCESS, null, serviceInfo);
    }

    public static <E> CommonResultVO<E> successWithoutData(String serviceName, String servicePort, String serviceDescription) {
        ServiceInfo serviceInfo = new ServiceInfo(serviceName, servicePort, serviceDescription);
        return successWithoutData(serviceInfo);
    }

    public static <E> CommonResultVO<E> successWithData(E data, ServiceInfo serviceInfo) {
        return new CommonResultVO<>(PaymentConstant.CODE_SUCCESS, PaymentConstant.MESSAGE_SUCCESS, data, serviceInfo);
    }

    public static <E> CommonResultVO<E> successWithData(E data,
                                                        String serviceName,
                                                        String servicePort,
                                                        String serviceDescription) {
        ServiceInfo serviceInfo = new ServiceInfo(serviceName, servicePort, serviceDescription);
        return successWithData(data, serviceInfo);
    }

    public static <E> CommonResultVO<E> failedWithoutData(ServiceInfo serviceInfo) {
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, null, serviceInfo);
    }

    public static <E> CommonResultVO<E> failedWithoutData(String serviceName, String servicePort, String serviceDescription) {
        ServiceInfo serviceInfo = new ServiceInfo(serviceName, servicePort, serviceDescription);
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, null, serviceInfo);
    }

    public static <E> CommonResultVO<E> failedWithData(E data, ServiceInfo serviceInfo) {
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, data, serviceInfo);
    }

    public static <E> CommonResultVO<E> failedWithData(E data,
                                                       String serviceName,
                                                       String servicePort,
                                                       String serviceDescription) {
        ServiceInfo serviceInfo = new ServiceInfo(serviceName, servicePort, serviceDescription);
        return new CommonResultVO<>(PaymentConstant.CODE_FAILED, PaymentConstant.MESSAGE_FAILED, data, serviceInfo);
    }
}
