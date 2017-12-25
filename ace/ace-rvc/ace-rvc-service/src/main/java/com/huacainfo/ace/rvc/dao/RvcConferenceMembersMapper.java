package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceMembers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RvcConferenceMembersMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceMembers record);

    int insertSelective(RvcConferenceMembers record);

    RvcConferenceMembers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceMembers record);

    int updateByPrimaryKey(RvcConferenceMembers record);

    RvcConferenceMembers getByUserId(@Param("userId") String userId,
                                     @Param("confId") String confId);

    /**
     * 获取与会人员列表
     *
     * @param conferenceId 会议ID
     * @return list
     */
    List<RvcConferenceMembers> getMemberList(String conferenceId);

    /**
     * 全部签到
     * <p>
     * //     * @param conferenceId   会议ID
     * //     * @param status         签到状态， 0-未签，1-已签到
     * //     * @param lastModifyTime 最后修改时间
     *
     * @return 修改记录条数
     */
    int allSignIn(Map<String, Object> params);
}