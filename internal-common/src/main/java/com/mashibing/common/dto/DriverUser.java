package com.mashibing.common.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: DriverUser
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
@Data
public class DriverUser {

    private Long id;
    private String address;
    private String driverName;
    private String driverPhone;
    private Integer driverGender;
    private LocalDate driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
    private LocalDate getDriverLicenseDate;
    private LocalDate driverLicenseOn;
    private LocalDate driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
    private LocalDate networkCarIssueDate;
    private LocalDate getNetworkCarProofDate;
    private LocalDate networkCarProofOn;
    private LocalDate networkCarProofOff;
    private LocalDate registerDate;
    private Integer commercialType;
    private String contractCompany;
    private LocalDate contractOn;
    private LocalDate contractOff;
    private Integer state;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
