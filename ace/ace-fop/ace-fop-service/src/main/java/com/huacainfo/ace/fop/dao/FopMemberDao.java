package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FopMemberDao {

    FopMember selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopMember record);

    int insertSelective(FopMember record);

    int updateByPrimaryKey(FopMember record);

    int updateByPrimaryKeySelective(FopMember record);

    FopMemberVo selectVoByPrimaryKey(String id);

    List<FopMemberVo> findList(@Param("condition") FopMemberQVo condition,
                               @Param("start") int start, @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopMemberQVo condition);

    int isExit(FopMember record);


    /**
     * @param relationType 关联类型  0-企业会员 1-团体会员
     * @param relationId   关联ID
     * @param status       数据状态 1-正常  -1-已删除
     * @return
     * @Author Arvin
     */
    List<FopMember> selectByRelationType(@Param("relationType") String relationType,
                                         @Param("relationId") String relationId,
                                         @Param("status") String[] status);

    List<Map<String, Object>> selectRoleList(@Param("activeSyId") String activeSyId,
                                             @Param("roleTypes") String[] roleTypes);

}