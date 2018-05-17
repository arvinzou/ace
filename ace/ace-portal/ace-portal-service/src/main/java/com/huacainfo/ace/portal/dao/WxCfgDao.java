package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.vo.WxCfgQVo;
import com.huacainfo.ace.portal.vo.WxCfgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WxCfgDao {
    int deleteByPrimaryKey(String WxCfgId);

    int insert(WxCfg record);


    WxCfgVo selectByPrimaryKey(String WxCfgId);


    int updateByPrimaryKey(WxCfg record);
    
    List<WxCfgVo> findList(@Param("condition") WxCfgQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") WxCfgQVo condition);

	int isExit(WxCfg record);

    int updateAccessTokenTicket(@Param("appId") String appId, @Param("accessToken") String accessToken, @Param("expiresIn") int expiresIn, @Param("ticket") String ticket);
    List<Map<String,Object>> selectAppList();

    Map<String, String> selectAccessTokenAndTicketByDeptId(String id);

    /**
     * 功能描述: 根据公众号原始ID，查询公众号配置信息
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/16 10:21
     */
    WxCfg findByOriginalId(String originalId);

    /**
     * 功能描述: 根据关键字，查询问题答案
     * <p> 目前仅市工商联使用，查询是工商联数据库数据</p>
     *
     * @param keyWord
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/16 11:54
     */
    List<Map<String, Object>> selectQuestion(String keyWord);

    int insertQuestion(@Param("params") Map<String, Object> params);
}