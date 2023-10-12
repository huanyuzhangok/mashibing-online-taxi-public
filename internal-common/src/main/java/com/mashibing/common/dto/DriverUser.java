package com.mashibing.common.dto;

import lombok.Data;

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
    private Date driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
    private Date getDriverLicenseDate;
    private Date driverLicenseOn;
    private Date driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
    private Date networkCarIssueDate;
    private Date getNetworkCarProofDate;
    private Date networkCarProofOn;
    private Date network_car_proof_off;
    private Date register_date;
    private Integer commercial_type;
    private String contract_company;
    private Date contract_on;
    private Date contract_off;
    private Integer state;
    private Date gmt_create;
    private Date gmt_modified;

}
