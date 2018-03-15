
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.License;


public class LicenseVo extends License {
    private static final long serialVersionUID = 1L;

    private String licenseTypeName;

    public String getLicenseTypeName() {
        //1: '身份证', 2: '驾驶证', 3: '行驶证', 4: '营业证', 5: '交通运输许可证'
        switch (getLicenseType()) {
            case "1":
                return "身份证";
            case "2":
                return "驾驶证";
            case "3":
                return "行驶证";
            case "4":
                return "营业证";
            case "5":
                return "交通运输许可证";
            default:
                return "未知证件类型";
        }
    }

    public void setLicenseTypeName(String licenseTypeName) {
        this.licenseTypeName = licenseTypeName;
    }
}
