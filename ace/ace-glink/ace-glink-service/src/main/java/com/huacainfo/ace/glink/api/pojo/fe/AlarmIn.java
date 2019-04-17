package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName AlarmIn
 * @Description 报警推送：   接收参数
 * @Author Arvin Zou
 * @Date 2019/4/17 16:19
 */
public class AlarmIn extends BaseModel {
    private String AreaNodeID;//区域节点编号
    private String AlarmType;//报警分类
    private String AlarmContent;//报警内容描述
    private String AlarmDateTime;//报警时间

    public String getAreaNodeID() {
        return AreaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        AreaNodeID = areaNodeID;
    }

    public String getAlarmType() {
        return AlarmType;
    }

    public void setAlarmType(String alarmType) {
        AlarmType = alarmType;
    }

    public String getAlarmContent() {
        return AlarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        AlarmContent = alarmContent;
    }

    public String getAlarmDateTime() {
        return AlarmDateTime;
    }

    public void setAlarmDateTime(String alarmDateTime) {
        AlarmDateTime = alarmDateTime;
    }
}
