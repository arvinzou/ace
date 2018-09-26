package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.BannerDao;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.service.BannerService;
import com.huacainfo.ace.jxb.vo.BannerQVo;
import com.huacainfo.ace.jxb.vo.BannerVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("bannerService")
/**
 * @author: 陈晓克
 * @version: 2018-09-26
 * @Description: TODO(轮播图)
 */
public class BannerServiceImpl implements BannerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(轮播图分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BannerVo>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public PageResult<BannerVo> findBannerList(BannerQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<BannerVo> rst = new PageResult<>();
        List<BannerVo> list = this.bannerDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.bannerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBanner
     * @Description: TODO(添加轮播图)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse insertBanner(Banner o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getImgUrl())) {
            return new MessageResponse(1, "图片URL不能为空！");
        }
        if (CommonUtils.isBlank(o.getUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateTime(new Date());
        o.setStatus("1");
        this.bannerDao.insert(o);
        this.dataBaseLogService.log("添加轮播图", "轮播图", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateBanner
     * @Description: TODO(更新轮播图)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse updateBanner(Banner o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getImgUrl())) {
            return new MessageResponse(1, "图片URL不能为空！");
        }
        if (CommonUtils.isBlank(o.getUrl())) {
            return new MessageResponse(1, "资源地址不能为空！");
        }
        this.bannerDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更轮播图", "轮播图", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectBannerByPrimaryKey
     * @Description: TODO(获取轮播图)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Banner>
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public SingleResult<BannerVo> selectBannerByPrimaryKey(String id) throws Exception {
        SingleResult<BannerVo> rst = new SingleResult<>();
        rst.setValue(this.bannerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBannerByBannerId
     * @Description: TODO(删除轮播图)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse deleteBannerByBannerId(String id, UserProp userProp) throws
            Exception {
        this.bannerDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除轮播图", "轮播图", id, id,
                "轮播图", userProp);
        return new MessageResponse(0, "轮播图删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核轮播图)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        return new MessageResponse(0, "轮播图审核完成！");
    }

}
