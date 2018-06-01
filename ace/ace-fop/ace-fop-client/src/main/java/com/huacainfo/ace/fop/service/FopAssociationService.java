package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAssMember;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopAssociationService {


    PageResult<FopAssociationVo> findFopAssociationList(FopAssociationQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertFopAssociation(FopAssociation obj, UserProp userProp) throws Exception;

    MessageResponse insertAssociation(String name, String phoneNumber) throws Exception;

    MessageResponse updateFopAssociation(FopAssociation obj, UserProp userProp) throws Exception;

    SingleResult<FopAssociationVo> selectFopAssociationByPrimaryKey(String id) throws Exception;

    ResultResponse selectAssociationByPrimaryKey(UserProp userProp) throws Exception;

    MessageResponse deleteFopAssociationByFopAssociationId(String id, UserProp userProp) throws Exception;

    FopAssociation selectByDepartmentId(String departmentId) throws Exception;

}
