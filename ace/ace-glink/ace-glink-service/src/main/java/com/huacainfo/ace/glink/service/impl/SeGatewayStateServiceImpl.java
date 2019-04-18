package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.TopBuildingDao;
import com.huacainfo.ace.glink.vo.TopBuildingVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.SeGatewayStateDao;
import com.huacainfo.ace.glink.model.SeGatewayState;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeGatewayStateService;
import com.huacainfo.ace.glink.vo.SeGatewayStateVo;
import com.huacainfo.ace.glink.vo.SeGatewayStateQVo;

@Service("seGatewayStateService")
/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(网关数据)
 */
public class SeGatewayStateServiceImpl implements SeGatewayStateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeGatewayStateDao seGatewayStateDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(网关数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeGatewayStateVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeGatewayStateVo> findSeGatewayStateList(SeGatewayStateQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        SeGatewayStateDao dao = session.getMapper(SeGatewayStateDao.class);
        PageResult<SeGatewayStateVo> rst = new PageResult<>();
        try {
            List<SeGatewayStateVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (rst.getTotal()  <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeGatewayState
     * @Description: TODO(添加网关数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeGatewayState(List<SeGatewayState> list, UserProp userProp) throws Exception {
        if(list.size()>0){
            for(int i=0; i<list.size(); i++){
                SeGatewayState o = list.get(i);
                String guid = StringUtil.isEmpty(list.get(i).getId()) ? GUIDUtil.getGUID() : list.get(i).getId();
                o.setId(guid);
                if (CommonUtils.isBlank(o.getNodeID())) {
                    return new MessageResponse(1, "配电箱编号不能为空！");
                }
                int temp = this.seGatewayStateDao.isExit(o);
                if (temp > 0) {
                    return new MessageResponse(1, "网关数据名称重复！");
                }
                o.setCreateDate(new Date());
                this.seGatewayStateDao.insert(o);
                this.dataBaseLogService.log("添加网关数据", "网关数据", "",
                        o.getId(), o.getId(), userProp);
            }
        }

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeGatewayState
     * @Description: TODO(更新网关数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeGatewayState(SeGatewayState o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        this.seGatewayStateDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更网关数据", "网关数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeGatewayStateByPrimaryKey
     * @Description: TODO(获取网关数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeGatewayState>
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeGatewayStateVo> selectSeGatewayStateByPrimaryKey(String id) throws Exception {
        SingleResult<SeGatewayStateVo> rst = new SingleResult<>();
        rst.setValue(this.seGatewayStateDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeGatewayStateBySeGatewayStateId
     * @Description: TODO(删除网关数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeGatewayStateBySeGatewayStateId(String id, UserProp userProp) throws
            Exception {
        this.seGatewayStateDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除网关数据", "网关数据", id, id,
                "网关数据", userProp);
        return new MessageResponse(0, "删除成功！");
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
     * @author: luocan
     * @version: 2019-04-18
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            SeGatewayState o = new SeGatewayState();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getNodeID())) {
                return new MessageResponse(1, "配电箱编号不能为空！");
            }

            int t = this.seGatewayStateDao.isExit(o);
            if (t > 0) {
                this.seGatewayStateDao.updateByPrimaryKey(o);

            } else {
                this.seGatewayStateDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("网关数据导入", "网关数据", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除网关数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeGatewayStateBySeGatewayStateIds(List<String> list, UserProp userProp)
            throws Exception {

        this.seGatewayStateDao.deleteByPrimaryKeys(list);
        this.dataBaseLogService.log("批量删除网关数据", "网关数据", list.get(0),
                list.get(0), "网关数据", userProp);
        return new MessageResponse(0, "删除成功！");

    }

}
