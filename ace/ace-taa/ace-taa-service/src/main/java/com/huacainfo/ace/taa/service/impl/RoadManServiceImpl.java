package com.huacainfo.ace.taa.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.List;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.taa.dao.RoadManDao;
import com.huacainfo.ace.taa.model.RoadMan;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.service.RoadManService;
import com.huacainfo.ace.taa.vo.RoadManVo;
import com.huacainfo.ace.taa.vo.RoadManQVo;
@Service("roadManService")
/**
* @author: 陈晓克
* @version: 2019-01-04
* @Description:  TODO(路长)
*/
public class RoadManServiceImpl implements RoadManService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private RoadManDao roadManDao;
@Autowired
private DataBaseLogService dataBaseLogService;


/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(路长分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<RoadManVo>
    * @throws
    * @author: 陈晓克
    * @version: 2019-01-04
    */
    @Override
    public PageResult
    <RoadManVo> findRoadManList(RoadManQVo condition, int start,int limit, String orderBy) throws Exception {
        PageResult<RoadManVo> rst = new PageResult<>();
            List<RoadManVo> list = this.roadManDao.findList(condition,
                start, limit, orderBy);
                rst.setRows(list);
                if (start <= 1) {
                int allRows = this.roadManDao.findCount(condition);
                rst.setTotal(allRows);
                }
                return rst;
                }

                /**
                *
                * @Title:insertRoadMan
                * @Description: TODO(添加路长)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: 陈晓克
                * @version: 2019-01-04
                */
                @Override
                public MessageResponse insertRoadMan(RoadMan o, UserProp userProp) throws Exception {

                if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getAreaCode())) {return new MessageResponse(1, "行政区划不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "姓名不能为空！");}if (CommonUtils.isBlank(o.getMobile())) {return new MessageResponse(1, "手机号不能为空！");}if (CommonUtils.isBlank(o.getStartName())) {return new MessageResponse(1, "管辖路段开始不能为空！");}if (CommonUtils.isBlank(o.getEndName())) {return new MessageResponse(1, "管辖路段截止不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "状态 不能为空！");}

                int temp = this.roadManDao.isExit(o);
                if (temp > 0) {
                return new MessageResponse(1, "路长名称重复！");
                }

                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                this.roadManDao.insert(o);
                this.dataBaseLogService.log("添加路长", "路长", "",
                o.getId(),o.getId(), userProp);

                return new MessageResponse(0, "添加路长完成！");
                }

                /**
                *
                * @Title:updateRoadMan
                * @Description: TODO(更新路长)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: 陈晓克
                * @version: 2019-01-04
                */
                @Override
                public MessageResponse updateRoadMan(RoadMan o, UserProp userProp) throws Exception {
                if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getAreaCode())) {return new MessageResponse(1, "行政区划不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "姓名不能为空！");}if (CommonUtils.isBlank(o.getMobile())) {return new MessageResponse(1, "手机号不能为空！");}if (CommonUtils.isBlank(o.getStartName())) {return new MessageResponse(1, "管辖路段开始不能为空！");}if (CommonUtils.isBlank(o.getEndName())) {return new MessageResponse(1, "管辖路段截止不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "状态 不能为空！");}

                o.setLastModifyDate(new Date());
                o.setLastModifyUserName(userProp.getName());
                o.setLastModifyUserId(userProp.getUserId());
                this.roadManDao.updateByPrimaryKey(o);
                this.dataBaseLogService.log("变更路长", "路长", "",
                o.getId(), o.getId(), userProp);

                return new MessageResponse(0, "变更路长完成！");
                }

                /**
                *
                * @Title:selectRoadManByPrimaryKey
                * @Description: TODO(获取路长)
                * @param: @param id
                * @param: @throws Exception
                * @return: SingleResult<RoadMan>
                * @throws
                * @author: 陈晓克
                * @version: 2019-01-04
                */
                @Override
                public SingleResult
                <RoadManVo> selectRoadManByPrimaryKey(String id) throws Exception {
                    SingleResult<RoadManVo> rst = new SingleResult<>();
                        rst.setValue(this.roadManDao.selectVoByPrimaryKey(id));
                        return rst;
                        }

                        /**
                        *
                        * @Title:deleteRoadManByRoadManId
                        * @Description: TODO(删除路长)
                        * @param: @param id
                        * @param: @param userProp
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: 陈晓克
                        * @version: 2019-01-04
                        */
                        @Override
                        public MessageResponse deleteRoadManByRoadManId(String id, UserProp userProp) throws
                        Exception {
                        this.roadManDao.deleteByPrimaryKey(id);
                        this.dataBaseLogService.log("删除路长", "路长", id, id,
                        "路长", userProp);
                        return new MessageResponse(0, "路长删除完成！");
                        }


                        /**
                        *
                        * @Title:audit
                        * @Description: TODO(审核路长)
                        * @param: @param id bean.id
                        * @param: @param rst 审核结果 3-通过 4-拒绝
                        * @param: @param remark 审核备注
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: 陈晓克
                        * @version: 2019-01-04
                        */
                        @Override
                        public MessageResponse audit(String id,String rst, String remark,
                        UserProp userProp) throws Exception {



                        dataBaseLogService.log("审核路长", "路长", id, id,
                        "路长", userProp);
                        return new MessageResponse(0, "路长审核完成！");
                        }


                         /**
                                    	 *
                                    	    * @Title:importXls
                                    	    * @Description:  TODO(Excel导入资源数据)
                                    	 		* @param:        @param list
                                    	 		* @param:        @param userProp
                                    	 		* @param:        @return
                                    	 		* @param:        @throws Exception
                                    	 		* @return:       MessageResponse
                                    	 		* @throws
                                    	    * @author: 陈晓克
                                    	    * @version: 2019-01-04
                                    	 */

                        @Override
                        public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
                        		int i = 1;
                        		for (Map<String, Object> row : list) {
                        			RoadMan o = new RoadMan();
                        			CommonBeanUtils.copyMap2Bean(o,row);
                        			o.setCreateTime(new Date());
                        			o.setCreateUserId(userProp.getUserId());
                        			o.setStauts("1");

                        			this.logger.info(o.toString());
                        			if (CommonUtils.isBlank(o.getResourcesId())) {
                        				return new MessageResponse(1,"行"+i+ ",编号不能为空！");
                        			}

                        			int t = this.roadManDao.isExit(o);
                        			if (t > 0) {
                        				this.roadManDao.updateByPrimaryKey(o);

                        			} else {
                        				this.roadManDao.insert(o);
                        			}
                        			i++;
                        		}
                        		this.dataBaseLogService.log("路长导入", "路长", "", "rs.xls","rs.xls", userProp);
                        		return new MessageResponse(0, "导入完成！");
                        	}


                        	 /**
                                 *
                                    * @Title:getList
                                    * @Description:  TODO(条件查询数据)
                                        * @param:        @param p
                                        * @param:        @return
                                        * @param:        @throws Exception
                                        * @return:       ListResult<Map<String,Object>>
                                        * @throws
                                    * @author: 陈晓克
                                    * @version: 2019-01-04
                                 */
                                   @Override
                                public ListResult<Map<String,Object>> getList(Map<String, Object> p) throws Exception{
                                 ListResult<Map<String,Object>> rst = new ListResult<>();
                                 rst.setValue(this.roadManDao.getList(p));

                                 return rst;

                                }

 }
