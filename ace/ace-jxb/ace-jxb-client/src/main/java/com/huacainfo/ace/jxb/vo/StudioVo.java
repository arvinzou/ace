package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.model.StudioImg;

import java.util.List;


public class StudioVo extends Studio {
    private static final long serialVersionUID = 1L;


    /**
     * 负责人姓名
     */
    private String dutyName;
    /**
     * 负责人肖像照
     */
    private String dutyHeadImg;

    /**
     * 工作室图片资料
     */
    private List<StudioImg> imgList;

    /**
     * 工作室成员列表
     */
    private List<CounselorVo> memberList;

    /**
     * 工作室成员数量
     */
    private int subNum;

    public int getSubNum() {
        return subNum;
    }

    public void setSubNum(int subNum) {
        this.subNum = subNum;
    }

    public List<CounselorVo> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<CounselorVo> memberList) {
        this.memberList = memberList;
    }

    public List<StudioImg> getImgList() {
        return imgList;
    }

    public void setImgList(List<StudioImg> imgList) {
        this.imgList = imgList;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyHeadImg() {
        return dutyHeadImg;
    }

    public void setDutyHeadImg(String dutyHeadImg) {
        this.dutyHeadImg = dutyHeadImg;
    }
}
