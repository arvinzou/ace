package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.CardDataDao;
import com.huacainfo.ace.partyschool.model.CardData;
import com.huacainfo.ace.partyschool.service.CardDataService;
import com.huacainfo.ace.partyschool.vo.CardDataQVo;
import com.huacainfo.ace.partyschool.vo.CardDataVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cardDataService")
/**
 * @author: Arvin
 * @version: 2019-01-08
 * @Description: TODO(一卡通绑定)
 */
public class CardDataServiceImpl implements CardDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CardDataDao cardDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(一卡通绑定分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult <CardDataVo>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public PageResult<CardDataVo> findCardDataList(CardDataQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        PageResult<CardDataVo> rst = new PageResult<>();
        List<CardDataVo> list = cardDataDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cardDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCardData
     * @Description: TODO(添加一卡通绑定)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse insertCardData(CardData o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCardData())) {
            return new MessageResponse(1, "卡号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户Id不能为空！");
        }
        int temp = this.cardDataDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "绑定关系重复,请核对！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.cardDataDao.insert(o);
        this.dataBaseLogService.log("添加一卡通绑定", "一卡通绑定", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加完成！");
    }

    /**
     * @throws
     * @Title:updateCardData
     * @Description: TODO(更新一卡通绑定)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse updateCardData(CardData o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键！");
        }
        if (CommonUtils.isBlank(o.getCardData())) {
            return new MessageResponse(1, "卡号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户不能为空！");
        }

        o.setStatus("1");
        o.setCreateDate(DateUtil.getNowDate());
        cardDataDao.updateByPrimaryKey(o);
        dataBaseLogService.log("变更一卡通绑定", "一卡通绑定", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更完成！");
    }

    /**
     * @throws
     * @Title:selectCardDataByPrimaryKey
     * @Description: TODO(获取一卡通绑定)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CardData>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public SingleResult<CardDataVo> selectCardDataByPrimaryKey(String id) throws Exception {
        SingleResult<CardDataVo> rst = new SingleResult<>();
        rst.setValue(this.cardDataDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCardDataByCardDataId
     * @Description: TODO(删除一卡通绑定)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse deleteCardDataByCardDataId(String id, UserProp userProp) throws Exception {
        this.cardDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除一卡通绑定", "一卡通绑定", id, id,
                "一卡通绑定", userProp);
        return new MessageResponse(0, "一卡通绑定删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核一卡通绑定)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        CardData obj = cardDataDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "一卡通绑定数据丢失");
        }
        obj.setStatus(rst);

        cardDataDao.updateStatus(obj);


        dataBaseLogService.log("审核一卡通绑定", "一卡通绑定", id, id, "一卡通绑定", userProp);
        return new MessageResponse(0, "一卡通绑定审核完成！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        List<Map<String, Object>> userList;
        Map<String, Object> user;
        Map<String, Object> params = new HashMap<>();
        for (Map<String, Object> row : list) {
            CardDataVo o = new CardDataVo();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setId(GUIDUtil.getGUID());
            this.logger.info(o.toString());

            if (CommonUtils.isBlank(o.getCardData())) {
                return new MessageResponse(1, "行" + i + "，卡号不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "行" + i + "，手机号码不能为空！");
            }
            //根据身份证号码，获取用户信息
            params.put("q", o.getMobile());
            userList = cardDataDao.findUserList(params);
            if (CollectionUtils.isEmpty(userList)) {
                return new MessageResponse(1, "行" + i + "，不存在此手机号码的用户！");
            }
            user = userList.get(0);
            o.setUserId(String.valueOf(user.get("id")));
            //存储数据
            int t = cardDataDao.isExist(o);
            if (t > 0) {
                cardDataDao.updateByUserId(o);
            } else {
                cardDataDao.insert(o);
            }
            i++;
        }
        dataBaseLogService.log("一卡通绑定导入", "一卡通绑定", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        //rst.setValue(this.cardDataDao.getList(p));

        return rst;
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = null;//this.cardDataDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除一卡通绑定
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    public MessageResponse deleteCardDataByCardDatas(String[] id, UserProp userProp) throws Exception {

        //cardDataDao.deleteByPrimaryKeys(id);
        dataBaseLogService.log("批量删除一卡通绑定", "一卡通绑定", id[0], id[0], "一卡通绑定", userProp);
        return new MessageResponse(0, "删除成功！");

    }

    /**
     * 加载系统所有用户列表，切条件模糊查询
     *
     * @param params 参数
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public Map<String, Object> findUserList(Map<String, Object> params) {
        List<Map<String, Object>> list = cardDataDao.findUserList(params);

        Map<String, Object> rst = new HashMap<>();
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

}
