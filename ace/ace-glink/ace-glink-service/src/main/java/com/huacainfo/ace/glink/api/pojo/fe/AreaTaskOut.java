package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName AreaTaskOut
 * @Description 区域任务数据
 * @Author Arvin Zou
 * @Date 2019/4/17 15:18
 */
public class AreaTaskOut extends BaseModel {

    public AreaTaskOut() {
    }

    public AreaTaskOut(int taskCount, List<AreaTaskOut.TaskData> taskData) {
        TaskCount = taskCount;
        TaskData = taskData;
    }

    private int TaskCount;              //任务数量
    private List<TaskData> TaskData;    //任务数据集合

    public int getTaskCount() {
        return TaskCount;
    }

    public void setTaskCount(int taskCount) {
        TaskCount = taskCount;
    }

    public List<AreaTaskOut.TaskData> getTaskData() {
        return TaskData;
    }

    public void setTaskData(List<AreaTaskOut.TaskData> taskData) {
        TaskData = taskData;
    }

    /**
     * static class
     */
    public static class TaskData extends BaseModel {

        private String AreaNodeID;      //区域编号
        private int TaskNo;             //任务编号
        private String TaskName;        //任务名称

        public String getAreaNodeID() {
            return AreaNodeID;
        }

        public void setAreaNodeID(String areaNodeID) {
            AreaNodeID = areaNodeID;
        }

        public int getTaskNo() {
            return TaskNo;
        }

        public void setTaskNo(int taskNo) {
            TaskNo = taskNo;
        }

        public String getTaskName() {
            return TaskName;
        }

        public void setTaskName(String taskName) {
            TaskName = taskName;
        }
    }
}
