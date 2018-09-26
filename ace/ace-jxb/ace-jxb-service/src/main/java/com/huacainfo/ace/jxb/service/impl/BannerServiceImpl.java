package com.huacainfo.ace.jxb.service.impl;


import java.util.Date;
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
import com.huacainfo.ace.jxb.dao.BannerDao;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.BannerService;
import com.huacainfo.ace.jxb.vo.BannerVo;
import com.huacainfo.ace.jxb.vo.BannerQVo;

@Service("bannerService")
/**
 * @author: huacai003
 * @version: 2018-09-26
 * @Description: TODO(banner)
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
     * @Description: TODO(banner分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BannerVo>
     * @author: huacai003
     * @version: 2018-09-26
     */
    @Override
    public PageResult
            <BannerVo> findBannerList(BannerQVo condition, int start,
                                      int limit, String orderBy) throws Exception {
        PageResult
                <BannerVo> rst = new PageResult<>();
        List
                <BannerVo> list = this.bannerDao.findList(condition,
                start, limit, orderBy);
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
     * @Description: TODO(添加banner)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse insertBanner(Banner o, UserProp userProp) throws Exception {

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
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "入库日期不能为空！");
        }


        int temp = this.bannerDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "banner名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateTime(new Date());
        o.setStatus("1");
        this.bannerDao.insert(o);
        this.dataBaseLogService.log("添加banner", "banner", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加banner完成！");
    }

    /**
     * @throws
     * @Title:updateBanner
     * @Description: TODO(更新banner)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
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
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "入库日期不能为空！");
        }
        this.bannerDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更banner", "banner", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更banner完成！");
    }

    /**
     * @throws
     * @Title:selectBannerByPrimaryKey
     * @Description: TODO(获取banner)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Banner>
     * @author: huacai003
     * @version: 2018-09-26
     */
    @Override
    public SingleResult
            <BannerVo> selectBannerByPrimaryKey(String id) throws Exception {
        SingleResult
                <BannerVo> rst = new SingleResult<>();
        rst.setValue(this.bannerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBannerByBannerId
     * @Description: TODO(删除banner)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-26
     */
    @Override
    public MessageResponse deleteBannerByBannerId(String id, UserProp userProp) throws
            Exception {
        this.bannerDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除banner", "banner", id, id,
                "banner", userProp);
        return new MessageResponse(0, "banner删除完成！");
    }

}
