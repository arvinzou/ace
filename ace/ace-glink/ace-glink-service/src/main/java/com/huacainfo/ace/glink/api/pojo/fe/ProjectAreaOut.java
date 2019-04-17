package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName ProjectAreaOut
 * @Description 获取项目区域数据 返回对象
 * @Author Arvin Zou
 * @Date 2019/4/17 10:14
 */
public class ProjectAreaOut extends BaseModel {

    private String ProjectName;     //项目名称
    private String AreaName;        //区域、目录名称
    private int AreaNodeCount;      //子项数量
    private String AreaNodeID;      //区域、目录ID
    private int AreaType;           //类型 0-目录，1-区域
    private String AreaNode;        //子项集合

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public int getAreaNodeCount() {
        return AreaNodeCount;
    }

    public void setAreaNodeCount(int areaNodeCount) {
        AreaNodeCount = areaNodeCount;
    }

    public String getAreaNodeID() {
        return AreaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        AreaNodeID = areaNodeID;
    }

    public int getAreaType() {
        return AreaType;
    }

    public void setAreaType(int areaType) {
        AreaType = areaType;
    }

    public String getAreaNode() {
        return AreaNode;
    }

    public void setAreaNode(String areaNode) {
        AreaNode = areaNode;
    }

    /**
     * static class
     */
    public static class AreaNodeItem extends BaseModel {
        private String AreaName;        //区域、目录名称
        private int AreaNodeCount;      //子项数量
        private String AreaNodeID;      //区域、目录ID
        private int AreaType;           //类型 0-目录，1-区域
        private String AreaNode;        //子项集合

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String areaName) {
            AreaName = areaName;
        }

        public int getAreaNodeCount() {
            return AreaNodeCount;
        }

        public void setAreaNodeCount(int areaNodeCount) {
            AreaNodeCount = areaNodeCount;
        }

        public String getAreaNodeID() {
            return AreaNodeID;
        }

        public void setAreaNodeID(String areaNodeID) {
            AreaNodeID = areaNodeID;
        }

        public int getAreaType() {
            return AreaType;
        }

        public void setAreaType(int areaType) {
            AreaType = areaType;
        }

        public String getAreaNode() {
            return AreaNode;
        }

        public void setAreaNode(String areaNode) {
            AreaNode = areaNode;
        }
    }

}
