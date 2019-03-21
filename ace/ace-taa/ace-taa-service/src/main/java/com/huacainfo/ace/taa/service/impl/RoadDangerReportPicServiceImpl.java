package com.huacainfo.ace.taa.service.impl;


import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.taa.dao.RoadDangerReportPicDao;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.service.RoadDangerReportPicService;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicQVo;

@Service("roadDangerReportPicService")
/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public class RoadDangerReportPicServiceImpl implements RoadDangerReportPicService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDangerReportPicDao roadDangerReportPicDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportPicVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public PageResult<RoadDangerReportPicVo> findRordDangerReportPicList(RoadDangerReportPicQVo condition, int start, int limit, String orderBy) throws Exception {
      /*  PageResult<RordDangerReportPicVo> rst = new PageResult<>();
        List<RordDangerReportPicVo> list = this.roadDangerReportPicDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roadDangerReportPicDao.findCount(condition);
            rst.setTotal(allRows);
        }*/
        return null;//rst
    }

    /**
     * @throws
     * @Title:insertRordDangerReportPic
     * @Description: TODO(添加路况上报)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse insertRordDangerReportPic(RoadDangerReportPic o, UserProp userProp) throws Exception {


        //  int temp = this.roadDangerReportPicDao.isExit(o);
        // if (temp > 0) {
        /*    return new MessageResponse(1, "路况上报名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.roadDangerReportPicDao.insert(o);
        this.dataBaseLogService.log("添加路况上报", "路况上报", "",
                o.getId(), o.getId(), userProp);
*/
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateRordDangerReportPic
     * @Description: TODO(更新路况上报)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse updateRordDangerReportPic(RoadDangerReportPic o, UserProp userProp) throws Exception {


//        this.roadDangerReportPicDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更路况上报", "路况上报", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectRordDangerReportPicByPrimaryKey
     * @Description: TODO(获取路况上报)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReportPic>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public SingleResult<RoadDangerReportPicVo> selectRordDangerReportPicByPrimaryKey(String id) throws Exception {
        SingleResult<RoadDangerReportPicVo> rst = new SingleResult<>();
        //   rst.setValue(this.roadDangerReportPicDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRordDangerReportPicByRordDangerReportPicId
     * @Description: TODO(删除路况上报)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse deleteRordDangerReportPicByRordDangerReportPicId(String id, UserProp userProp) throws
            Exception {
//        this.roadDangerReportPicDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路况上报", "路况上报", id, id,
                "路况上报", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路况上报)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核路况上报", "路况上报", id, id,
                "路况上报", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        //  rst.setValue(this.roadDangerReportPicDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
      /*  Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.roadDangerReportPicDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);*/
        return null;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除路况上报 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse deleteRordDangerReportPicByRordDangerReportPicIds(String[] id, UserProp userProp) throws Exception {

        // this.roadDangerReportPicDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除路况上报", "路况上报", id[0], id[0], "路况上报", userProp);
        return new MessageResponse(0, "删除成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        //this.roadDangerReportPicDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "路况上报", id, id, "路况上报", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 查看路患现场图片
     *
     * @param id
     * @return
     */
    @Override
    public List<RoadDangerReportPic> selectNochangedMethodImg(String id) {
        List<RoadDangerReportPic> ds = roadDangerReportPicDao.selectNochangedMethodImg(id);
        return ds;

    }

    /**
     * 查看路患整改图片
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<RoadDangerReportPic> selectChangedMethodImg(String id) throws Exception {
        List<RoadDangerReportPic> ds = roadDangerReportPicDao.selectChangedMethodImg(id);
        return ds;
    }


    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        return null;
    }
}
