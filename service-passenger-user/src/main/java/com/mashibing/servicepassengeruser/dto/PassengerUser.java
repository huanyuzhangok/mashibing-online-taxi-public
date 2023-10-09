package com.mashibing.servicepassengeruser.dto;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @className: PassengerUser
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/
@Data
public class PassengerUser {
    private long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;
}
