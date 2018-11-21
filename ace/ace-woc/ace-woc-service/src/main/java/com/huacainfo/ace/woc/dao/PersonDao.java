package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.woc.vo.PersonQVo;
import com.huacainfo.ace.woc.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    Person selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    PersonVo selectByPrimaryKeyVo(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List<PersonVo> findList(@Param("condition") PersonQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PersonQVo condition);

    int isExit(Person record);

    List<Map<String, String>> selectPerson(
            @Param("params") Map<String, Object> params);

    List<Person> selectAll();

    Person findByPlateNo(String plateNo);

    /**
     * 含accessToken等信息
     *
     * @param phoneNumber
     * @return
     */
    Map<String, Object> findWeChatInfo(String phoneNumber);

    int isExitByMobile(String mobile);

    /**
     * 含微信用户主体信息
     *
     * @param mobile
     * @return
     */
    Map<String, Object> findUserInfoByMobile(String mobile);

    int updateMobileByOpenId(@Param("mobile") String mobile,
                             @Param("openid") String openid);

    Map<String,Object> findUserInfoByOpenId(String openId);

    PersonVo findByMobile(String mobile);
}