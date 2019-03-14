package qyplugin.pojo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName BaseApiResponse
 * @Description TODO
 * @Author HuaCai008
 * @Date 2019/3/14 11:44
 */
public class BaseApiResponse extends BaseModel {
    /**
     * 1为成功，其它失败
     */
    private int status;
    /**
     * 如果失败有返回
     */
    private String error;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
